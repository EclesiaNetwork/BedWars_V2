package eu.eclesia_network.bedwars.module.modules.mysql;

import java.sql.ResultSet;

public interface MysqlUse {

    public int readOption = ResultSet.CONCUR_READ_ONLY;
    public int updateOption = ResultSet.CONCUR_UPDATABLE;
    public int scollOption = ResultSet.TYPE_SCROLL_SENSITIVE;
    public String[] tables = new String[] {"arena", "players", "games"};
    public String uuid = "uuid VARCHAR(36) ";
    public String arenaId = "arenaId INT(64) ";
    public String arenaState = "arenaState INT(1) ";
    public String gameId = "generatorId INT(64) ";
    public String generatorType = "generatorType VARCHAR(36) ";
    public String primaryKey = "PRIMARY KEY ";
    public String uniqueKey = "UNIQUE KEY ";
    public String notnull = "NOT NULL ";
    public String auto = "AUTO_INCREMENT ";

}
