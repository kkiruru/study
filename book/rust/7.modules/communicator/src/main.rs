extern crate communicator;


fn main() {
    of::nested_modules();

    let red = Red;
    let yellow = Yellow;
    let green = TrafficLight::Green;
}

pub mod a {
    pub mod series {
        pub mod of {
            pub fn nested_modules() {
                println!("nested modules")
            }
        }
    }
}

use a::series::of;

enum TrafficLight {
    Red,
    Yellow,
    Green,
}

use TrafficLight::{Red, Yellow};
