package com.csmithswim;
//list of golems with various levels

import java.util.ArrayList;
import java.util.List;

public class Den {

    List<Golem> golems = new ArrayList<>();

    public void createGolem(int selection) {
        Golem golem = new Golem(selection);
        golems.add(golem);
    }

    public void displayGolems () {

    }



}

