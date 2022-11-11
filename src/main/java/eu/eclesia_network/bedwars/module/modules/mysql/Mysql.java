package eu.eclesia_network.bedwars.module.modules.mysql;

import com.google.common.collect.Iterables;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import eu.eclesia_network.bedwars.BedWars;
import eu.eclesia_network.bedwars.GState;
import eu.eclesia_network.bedwars.config.ConfigType;
import eu.eclesia_network.bedwars.module.ModuleType;
import eu.eclesia_network.bedwars.module.Module;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static eu.eclesia_network.bedwars.module.modules.mysql.MysqlUse.arenaState;


public class Mysql extends Module {

    private static BedWars plugin;
    public static String DB_IP;
    public static String DB_Name;
    public static String USER;
    public static String PASS;
    public static int POOLSIZE;
    public static int MAXLIFETIME;
    public static HikariDataSource dataSource;
    public static String states;


    public Mysql(BedWars plugin) {
        super(plugin, ModuleType.MYSQL);
        this.plugin = plugin;
    }

    @Override
    public void onEnable() {
        DB_IP = getPlugin().getConfigManager().getFile(ConfigType.MYSQL).getConfig().getString("address");
        DB_Name = getPlugin().getConfigManager().getFile(ConfigType.MYSQL).getConfig().getString("database");
        USER = getPlugin().getConfigManager().getFile(ConfigType.MYSQL).getConfig().getString("username");
        PASS = getPlugin().getConfigManager().getFile(ConfigType.MYSQL).getConfig().getString("password");
        POOLSIZE = getPlugin().getConfigManager().getFile(ConfigType.MYSQL).getConfig().getInt("maximum-pool-size");
        MAXLIFETIME = getPlugin().getConfigManager().getFile(ConfigType.MYSQL).getConfig().getInt("maxLifetime");
        setupPool();
        initTables();
    }

    @Override
    public void onDisable() {
        getPlugin().getConfigManager().getFile(ConfigType.MYSQL).getConfig().set("address", DB_IP);
        getPlugin().getConfigManager().getFile(ConfigType.MYSQL).getConfig().set("database", DB_Name);
        getPlugin().getConfigManager().getFile(ConfigType.MYSQL).getConfig().set("username", USER);
        getPlugin().getConfigManager().getFile(ConfigType.MYSQL).getConfig().set("password", PASS);
        getPlugin().getConfigManager().getFile(ConfigType.MYSQL).getConfig().set("maximum-pool-size", POOLSIZE);
        getPlugin().getConfigManager().getFile(ConfigType.MYSQL).getConfig().set("maxLifetime", MAXLIFETIME);
    }

    private void setupPool() {
        HikariConfig config = new HikariConfig();
        config.setPoolName("BedWarsMYSQLPool");

        // Pool Settings
        config.setMaximumPoolSize(POOLSIZE);
        config.setMaxLifetime(MAXLIFETIME * 1000);

        // Database URL
        config.setJdbcUrl("jdbc:mysql://" + DB_IP + "/" + DB_Name);

        // Auth
        config.setUsername(USER);
        config.setPassword(PASS);

        // Encoding
        config.addDataSourceProperty("characterEncoding", StandardCharsets.UTF_8);
        config.addDataSourceProperty("useUnicode", "true");

        // Random stuff
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("jdbcCompliantTruncation", "false");

        // Caching
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "275");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    private static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private void initTables(){

        Collection<String> tabExist = new ArrayList<>();
        Collection<String> useTable = Arrays.asList(MysqlUse.tables);
        try(Connection con = getConnection(); Statement st = con.createStatement(MysqlUse.scollOption, MysqlUse.readOption)){
            ResultSet result = st.executeQuery("SHOW TABLES");
            result.last();
            if(result.getRow() < 1){
                String exc = "";
                for(int i=0; i<useTable.size(); i++){
                    if(Iterables.get(useTable, i).equalsIgnoreCase("arena")) exc = MysqlUse.arenaId+MysqlUse.primaryKey+MysqlUse.notnull+MysqlUse.auto+", "+MysqlUse.arenaState+MysqlUse.notnull;
                    if(Iterables.get(useTable, i).equalsIgnoreCase("players")) exc = MysqlUse.uuid+MysqlUse.primaryKey+MysqlUse.uniqueKey+MysqlUse.notnull+", "+MysqlUse.arenaId;
                    if(Iterables.get(useTable, i).equalsIgnoreCase("games")) exc = MysqlUse.gameId+MysqlUse.primaryKey+MysqlUse.notnull+MysqlUse.auto+", "+MysqlUse.arenaId;
                    if(!exc.isEmpty()) st.executeUpdate("CREATE TABLE "+Iterables.get(useTable, i)+" ("+exc+")");
                }
            }else {
                int rows = result.getRow();
                result.first();
                for(int i=0; i<rows; i++){
                    tabExist.add(result.getString(1));
                    result.next();
                }
                String exc = "";
                for(int i=0; i<useTable.size(); i++){
                    if(tabExist.size() < useTable.size()){
                        if(Iterables.get(tabExist, i).equalsIgnoreCase(Iterables.get(useTable, i))) continue;
                        if(Iterables.get(useTable, i).equalsIgnoreCase("arena")) exc = MysqlUse.arenaId+MysqlUse.primaryKey+MysqlUse.notnull+MysqlUse.auto+", "+MysqlUse.arenaState+MysqlUse.notnull;
                        if(Iterables.get(useTable, i).equalsIgnoreCase("players")) exc = MysqlUse.uuid+MysqlUse.primaryKey+MysqlUse.uniqueKey+MysqlUse.notnull+", "+MysqlUse.arenaId;
                        if(Iterables.get(useTable, i).equalsIgnoreCase("games")) exc = MysqlUse.gameId+MysqlUse.primaryKey+MysqlUse.notnull+MysqlUse.auto+", "+MysqlUse.arenaId;
                        if(!exc.isEmpty()) st.executeUpdate("CREATE TABLE "+Iterables.get(useTable, i)+" ("+exc+")");
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public static void setState(GState state, String arenaId) {
        try (Connection con = getConnection(); Statement st = con.createStatement(MysqlUse.scollOption, MysqlUse.updateOption)){
            ResultSet result = st.executeQuery("SELECT arenaState FROM arena WHERE arenaId='"+arenaId+"'");
            if(result.getRow() < 1) st.executeUpdate("REPLACE INTO arena (arenaState)VALUE ('"+state+"')"); else st.executeUpdate("UPDATE arena SET arenaState='"+state+"' WHERE arenaId='"+arenaId+"'");
        }catch (SQLException e){ plugin.getLogger().warning(e.getMessage()); }
    }

    public static void getState(String arenaId) {

        try (Connection con = getConnection(); Statement st = con.createStatement(MysqlUse.scollOption, MysqlUse.readOption)) {
            ResultSet result = st.executeQuery("SELECT arenaState FROM arena WHERE arenaId='"+arenaId+"'");
            if (result.getRow() < 1) return;
            states = result.getString(1);

        }catch (SQLException e){ plugin.getLogger().warning(e.getMessage()); }

    }

    /*public static void createWorld() throws SQLException {
        try(Connection con = getConnection(); Statement st = con.createStatement(MysqlUse.scollOption, MysqlUse.updateOption)){
            ResultSet result = st.executeQuery("CREATE arena ")
        }

    }*/

}