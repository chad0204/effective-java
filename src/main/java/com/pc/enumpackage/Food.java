package com.pc.enumpackage;

public interface Food {

    enum Breakfast implements Food {
        DABING,DOUJIANG
    }

    enum LUNCH implements Food {
        HUOGUO,HUANGMENJI
    }

    class In implements Food {

    }
}
