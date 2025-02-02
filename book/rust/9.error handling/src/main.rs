fn main() {
    println!("Hello, world!");

    unrecoverable();

    recoverable();

    panic_or_result();
}

fn unrecoverable() {
    // panic!("carsh and burn");

    let _v = vec![1, 2, 3];
    // v[99];
}

use std::fs::File;
use std::io::ErrorKind;

fn recoverable() {
    //    let f: u32 = File::open("hello.txt");

    // let f = File::open("hello.txt");
    // let f = match f {
    //     Ok(file) => file,
    //     Err(error) => {
    //         panic!("There was a problem opening the file: {:?}", error)
    //     },
    // };

    let f = File::open("hello.txt");
    println!("1> {:?}", f);

    let f = match f {
        Ok(file) => file,
        Err(ref error) if error.kind() == ErrorKind::NotFound => match File::create("hello.txt") {
            Ok(fc) => fc,
            Err(e) => {
                panic!("Tried to crate file but there was a problem: {:?}", e)
            }
        },
        Err(error) => {
            panic!("There was a problem opening the file: {:?}", error)
        }
    };
    println!("2> {:?}", f);

    let f = File::open("hello.txt");
    let f = match f {
        Ok(file) => file,
        Err(error) => match error.kind() {
            ErrorKind::NotFound => match File::create("hello.txt") {
                Ok(fc) => fc,
                Err(e) => panic!("Problem creating the file: {:?}", e),
            },
            other_error => {
                panic!("Problem opening the file: {:?}", other_error)
            }
        },
    };
    println!("3> {:?}", f);

    let f = File::open("hello.txt").unwrap();
    println!("4> {:?}", f);

    let f = File::open("hello.txt").expect("Failed to open hello.txt");
    println!("5> {:?}", f);

    let f = read_username_from_file();
    println!(">> {:?}", f);

    let f = read_username_from_file1();
    println!(">> {:?}", f);

    let f = read_username_from_file2();
    println!(">> {:?}", f);

    // let f = File::open("hello.txt")?;
}

use std::io;
use std::io::Read;

fn read_username_from_file() -> Result<String, io::Error> {
    let f = File::open("hello.txt");

    let mut f = match f {
        Ok(file) => file,
        Err(e) => return Err(e),
    };

    let mut s = String::new();
    match f.read_to_string(&mut s) {
        Ok(_) => Ok(s),
        Err(e) => Err(e),
    }
}

fn read_username_from_file1() -> Result<String, io::Error> {
    let mut f = File::open("hello.txt")?;
    let mut s = String::new();
    f.read_to_string(&mut s)?;
    Ok(s)
}

fn read_username_from_file2() -> Result<String, io::Error> {
    let mut s = String::new();

    File::open("hello.txt")?.read_to_string(&mut s)?;

    Ok(s)
}

fn panic_or_result() {
    use std::net::IpAddr;

    let home = "127.0.0.1".parse::<IpAddr>().unwrap();
    println!("{:?}", home);
}

pub struct Guess {
    value: u32,
}

impl Guess {
    pub fn new(value: u32) -> Guess {
        if value < 1 || value > 100 {
            panic!("Guess value must be between 1 and 100, got {}.", value);
        }

        Guess { value }
    }

    pub fn value(&self) -> u32 {
        self.value
    }
}
