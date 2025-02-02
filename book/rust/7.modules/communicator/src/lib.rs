#[cfg(test)]
mod tests {
    use super::client;

    #[test]
    fn it_works() {
        assert_eq!(2 + 2, 4);

        client::connect();
    }
}

pub mod client;

pub mod network;

mod outermost {
    pub fn middle_function() {
        middle_secret_function();
        inside::inner_function();
        // inside::secrete_function();
    }

    fn middle_secret_function() {}

    mod inside {
        pub fn inner_function() {
            secrete_function();

            // ::outermost::middle_secret_function();
            super::middle_secret_function();
        }

        fn secrete_function() {}
    }
}

pub fn try_me() {
    outermost::middle_function();
    // outermost::middle_secret_function();
    // outermost::inside::inner_function();
    // outermost::inside::secrete_function();
}