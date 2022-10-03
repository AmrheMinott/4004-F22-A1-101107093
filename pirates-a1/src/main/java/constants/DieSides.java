package constants;

import java.util.ArrayList;
import java.util.Arrays;

public class DieSides {

    public static final String DIAMOND = "Diamond";
    public static final String GOLD = "Gold";

    public static final String MONKEY = "Monkey";
    public static final String PARROT = "Parrot";

    public static final String SKULL = "Skull";
    public static final String SWORD = "Sword";

    public static final String NONE = "-";

    public static final ArrayList<String> DICE_FACES = new ArrayList<>(
            Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.GOLD,
                    DieSides.SWORD, DieSides.PARROT, DieSides.DIAMOND));

}
