

public abstract class Beverage {
    String description = "제목 없음";

    public String getDescription(){
        return description;

    };

    public abstract double cost();
}


class Espresso extends Beverage {
    public Espresso() {
        description = "에스프레소";
    }

    @Override
    public double cost(){
        return 1.99;
    }
}


class HouseBlend extends Beverage {
    public HouseBlend(){
        description = "하우스 블렌드 커피";
    }

    @Override
    public double cost(){
        return 0.89;
    }
}

class DarkRoast extends Beverage {
    public DarkRoast(){
        description = "다크 로스트 커피";
    }

    @Override
    public double cost(){
        return 0.99;
    }
}

class Decaf extends Beverage {
    public Decaf(){
        description = "디카페인 커피";
    }

    @Override
    public double cost(){
        return 1.05;
    }
}
