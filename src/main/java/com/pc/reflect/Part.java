package com.pc.reflect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Part {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    private static List<Factory<? extends Part>> partFactories = new ArrayList<>();

    static {
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new CarbinFilter.Factory());
        partFactories.add(new OilFilter.Factory());
        partFactories.add(new FanBelt.Factory());
        partFactories.add(new GeneratorBelt.Factory());
        partFactories.add(new PowerSteeringBelt.Factory());

    }

    private static Random random = new Random(47);

    public static Part createRandom() {
        int i = random.nextInt(partFactories.size());
        return partFactories.get(i).create();
    }

}

class Filter extends Part {}

class FuelFilter extends Filter {
    public static class Factory implements com.pc.reflect.Factory<FuelFilter> {

        @Override
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}

class AirFilter extends Filter {
    public static class Factory implements com.pc.reflect.Factory<AirFilter> {
        @Override
        public AirFilter create() {
            return new AirFilter();
        }
    }
}


class CarbinFilter extends Filter {
    public static class Factory implements com.pc.reflect.Factory<CarbinFilter> {
        @Override
        public CarbinFilter create() {
            return new CarbinFilter();
        }
    }
}

class OilFilter extends Filter {
    public static class Factory implements com.pc.reflect.Factory<OilFilter> {
        @Override
        public OilFilter create() {
            return new OilFilter();
        }
    }
}

class Belt extends Part {}

class FanBelt extends Belt {
    public static class Factory implements com.pc.reflect.Factory<FanBelt> {

        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }
}

class GeneratorBelt extends Belt {
    public static class Factory implements com.pc.reflect.Factory<GeneratorBelt> {

        @Override
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}


class PowerSteeringBelt extends Filter {
    public static class Factory implements com.pc.reflect.Factory<PowerSteeringBelt> {

        @Override
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
    }
}