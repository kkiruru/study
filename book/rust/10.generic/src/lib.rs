// extern crate aggregator;

// use ::Summarizable;

struct WeatherForecast {
    high_temp: f64,
    low_temp: f64,
    chance_of_precipitation: f64,
}

// impl Summarizable for WeatherForecast {
//     fn summry(&self) -> String {
//         format!("The high will be {}, and the low will be {}. The change of
//         precipitation is {}%.", self.high_temp, self.low_temp,
//         self.chance_of_precipitation)
//     }
// }