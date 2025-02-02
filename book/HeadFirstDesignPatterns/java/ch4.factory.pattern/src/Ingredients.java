

interface PizzaIngredientFactory {
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggie[] createVeggies();
    public Clam createClam();
}


abstract class Dough {
    String name = "Dough";
}

abstract class Sauce {
    String name = "Sauce";
}

abstract class Cheese {
    String name = "Cheese";
}

abstract class Veggie {
    String name = "Veggie";
}

abstract class Pepperoni {
    String name = "Pepperoni";
}

abstract class Clam {
    String name = "Clam";
}


class ThinCrustDough extends Dough {
    public ThinCrustDough() {
        name = "ThinCrust";
    }
}


class MarinaraSauce extends Sauce {
    public MarinaraSauce() {
        name = "MarinaraSauce";
    }
}


class ReggianoCheese extends Cheese {
    public ReggianoCheese() {
        name = "ReggianoCheese";
    }
}


class Garlic extends Veggie {
    public Garlic() {
        name = "Garlic";
    }
}


class Onion extends Veggie {
    public Onion() {
        name = "Onion";
    }
}


class Mushroom extends Veggie {
    public Mushroom() {
        name = "Mushroom";
    }
}


class RedPepper extends Veggie {
    public RedPepper() {
        name = "RedPepper";
    }
}


class SlicedPepperoni extends Pepperoni {
    public SlicedPepperoni() {
        name = "SlicedPepperoni";
    }
}


class FreshClams extends Clam {
    public FreshClams() {
        name = "FreshClams";
    }
}
