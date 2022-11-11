/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Crypto Morin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eu.eclesia_network.bedwars.utility.universal;

import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.*;
import java.util.regex.Pattern;

public enum XPotion {
    ABSORPTION("ABSORB"),
    BAD_OMEN("OMEN_BAD", "PILLAGER"),
    BLINDNESS("BLIND"),
    CONDUIT_POWER("CONDUIT", "POWER_CONDUIT"),
    CONFUSION("NAUSEA", "SICKNESS", "SICK"),
    DAMAGE_RESISTANCE("RESISTANCE", "ARMOR", "DMG_RESIST", "DMG_RESISTANCE"),
    DOLPHINS_GRACE("DOLPHIN", "GRACE"),
    FAST_DIGGING("HASTE", "SUPER_PICK", "DIGFAST", "DIG_SPEED", "QUICK_MINE", "SHARP"),
    FIRE_RESISTANCE("FIRE_RESIST", "RESIST_FIRE", "FIRE_RESISTANCE"),
    GLOWING("GLOW", "SHINE", "SHINY"),
    HARM("INJURE", "DAMAGE", "HARMING", "INFLICT"),
    HEAL("HEALTH", "INSTA_HEAL", "INSTANT_HEAL", "INSTA_HEALTH", "INSTANT_HEALTH"),
    HEALTH_BOOST("BOOST_HEALTH", "BOOST", "HP"),
    HERO_OF_THE_VILLAGE("HERO", "VILLAGE_HERO"),
    HUNGER("STARVE", "HUNGRY"),
    INCREASE_DAMAGE("STRENGTH", "BULL", "STRONG", "ATTACK"),
    INVISIBILITY("INVISIBLE", "VANISH", "INVIS", "DISAPPEAR", "HIDE"),
    JUMP("LEAP", "JUMP_BOOST"),
    LEVITATION("LEVITATE"),
    LUCK("LUCKY"),
    NIGHT_VISION("VISION", "VISION_NIGHT"),
    POISON("VENOM"),
    REGENERATION("REGEN"),
    SATURATION("FOOD"),
    SLOW("SLOWNESS", "SLUGGISH"),
    SLOW_DIGGING("FATIGUE", "DULL", "DIGGING", "SLOW_DIG", "DIG_SLOW"),
    SLOW_FALLING("SLOW_FALL", "FALL_SLOW"),
    SPEED("SPRINT", "RUNFAST", "SWIFT", "FAST"),
    UNLUCK("UNLUCKY"),
    WATER_BREATHING("WATER_BREATH", "UNDERWATER_BREATHING", "UNDERWATER_BREATH", "AIR"),
    WEAKNESS("WEAK", "DONALD_TRUMP"),
    WITHER("DECAY");

    public static final EnumSet<XPotion> VALUES = EnumSet.allOf(XPotion.class);
    private static final Pattern FORMAT_PATTERN = Pattern.compile("\\d+|\\W+");
    private final String[] aliases;

    XPotion(String... aliases) {
        this.aliases = aliases;
    }

    private static String format(String name) {
        return FORMAT_PATTERN.matcher(
                name.trim().replace('-', '_').replace(' ', '_')).replaceAll("").toUpperCase(Locale.ENGLISH);
    }

    public static Optional<XPotion> matchXPotion(String potion) {
        Validate.notEmpty(potion, "Cannot match XPotion of a null or empty potion effect type");
        PotionEffectType idType = getIdFromString(potion);
        if (idType != null) return Optional.of(matchXPotion(idType));
        potion = format(potion);

        for (XPotion potions : VALUES)
            if (potions.name().equals(potion) || potions.anyMatchAliases(potion)) return Optional.ofNullable(potions);
        return Optional.empty();
    }

    public static XPotion matchXPotion(PotionEffectType type) {
        Objects.requireNonNull(type, "Cannot match XPotion of a null potion effect type");
        try {
            return valueOf(type.getName());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unsupported PotionEffectType: " + type.getName(), ex.getCause());
        }
    }

    @SuppressWarnings("deprecation")
    private static PotionEffectType getIdFromString(String type) {
        try {
            int id = Integer.parseInt(type);
            return PotionEffectType.getById(id);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static PotionEffect parsePotionEffectFromString(String potion) {
        if (Strings.isNullOrEmpty(potion) || potion.equalsIgnoreCase("none")) return null;
        String[] split = StringUtils.contains(potion, ',') ?
                StringUtils.split(StringUtils.deleteWhitespace(potion), ',') :
                StringUtils.split(potion.replaceAll("  +", " "), ' ');

        Optional<XPotion> typeOpt = matchXPotion(split[0]);
        if (!typeOpt.isPresent()) return null;
        PotionEffectType type = typeOpt.get().parsePotionEffectType();
        if (type == null) return null;

        int duration = 2400; // 20 ticks * 60 seconds * 2 minutes
        int amplifier = 0;
        try {
            if (split.length > 1) {
                duration = Integer.parseInt(split[1]) * 20;
                if (split.length > 2) amplifier = Integer.parseInt(split[2]) - 1;
            }
        } catch (NumberFormatException ignored) {
        }

        return new PotionEffect(type, duration, amplifier);
    }

    public static void addPotionEffectsFromString(Player player, List<String> effects) {
        if (effects == null || effects.isEmpty()) return;
        Objects.requireNonNull(player, "Cannot add potion effects to null player");

        for (String effect : effects) {
            PotionEffect potionEffect = parsePotionEffectFromString(effect);
            if (potionEffect != null) player.addPotionEffect(potionEffect, true);
        }
    }

    public static ThrownPotion throwPotion(LivingEntity entity, Color color, PotionEffect... effects) {
        Objects.requireNonNull(entity, "Cannot throw potion from null entity");
        @SuppressWarnings("deprecation")
        ItemStack potion = Material.getMaterial("SPLASH_POTION") == null ?
                new ItemStack(Material.POTION, 1, (short) 16398) : // or 16384?
                new ItemStack(Material.SPLASH_POTION);
        // TODO check why the fuck Lingering potion isn't supported.

        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setColor(color);
        if (effects != null) for (PotionEffect effect : effects) meta.addCustomEffect(effect, true);
        potion.setItemMeta(meta);

        ThrownPotion thrownPotion = entity.launchProjectile(ThrownPotion.class);
        thrownPotion.setItem(potion);
        return thrownPotion;
    }

    public static ItemStack buildItemWithEffects(Material type, Color color, PotionEffect... effects) {
        Objects.requireNonNull(type, "Cannot build an effected item with null type");
        Validate.isTrue(canHaveEffects(type), "Cannot build item with " + type.name() + " potion type");

        ItemStack item = new ItemStack(type);
        PotionMeta meta = (PotionMeta) item.getItemMeta();

        meta.setColor(color);
        meta.setDisplayName(type == Material.POTION ? "Potion" : type == Material.SPLASH_POTION ? "Splash Potion" :
                type == Material.TIPPED_ARROW ? "Tipped Arrow" : "Lingering Potion");
        if (effects != null) for (PotionEffect effect : effects) meta.addCustomEffect(effect, true);
        item.setItemMeta(meta);
        return item;
    }

    public static boolean canHaveEffects(Material material) {
        if (material == null) return false;
        return material.name().endsWith("POTION") || material.name().startsWith("TI"); // TIPPED_ARROW
    }

    private boolean anyMatchAliases(String potionEffect) {
        for (String alias : aliases)
            if (potionEffect.equals(alias) || potionEffect.equals(StringUtils.remove(alias, '_'))) return true;
        return false;
    }

    public PotionEffectType parsePotionEffectType() {
        return PotionEffectType.getByName(this.name());
    }

    public boolean isSupported() {
        return this.parsePotionEffectType() != null;
    }

    @Deprecated
    public PotionType getPotionType() {
        PotionEffectType type = this.parsePotionEffectType();
        return type == null ? null : PotionType.getByEffect(type);
    }

    public PotionEffect parsePotion(int duration, int amplifier) {
        PotionEffectType type = this.parsePotionEffectType();
        return type == null ? null : new PotionEffect(type, duration, amplifier);
    }


    public String[] getAliases() {
        return aliases;
    }

    @Override
    public String toString() {
        return WordUtils.capitalize(this.name().replace('_', ' ').toLowerCase(Locale.ENGLISH));
    }
}