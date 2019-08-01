package simplerpg.controller;

import java.util.Map;

public class PlayerCharacter
{
    private String name;
    private String race;
    private String clazz;
    private String gender;

    private int health = 10;
    private int attack = 5;
    private int defense = 5;

    public PlayerCharacter(Map<String, String> map)
    {
        this.name = map.get("name");
        this.race = map.get("race");
        this.clazz = map.get("clazz");
        this.gender = map.get("gender");
    }

    public int getHealth()
    {
        return health;
    }

    public int getAttack()
    {
        return attack;
    }

    public int getDefense()
    {
        return defense;
    }

    public void heal(int healing)
    {
        this.health += healing;
    }

    public void damage(int dmg)
    {
        this.health += dmg;
    }
}
