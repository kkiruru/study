fn main() {
    let s = String::from("hello");

    takes_ownership(s);
    // println!("{}", s);

    let s1 = gives_ownership();
    println!("{}", s1);

    let x = 5;
    makes_copy(x);

    let s2 = String::from("hello2");
    let s3 = takes_and_gives_back(s2);
    println!("{}", s3);

    let (s3, len ) = calculate_length(s3);
    println!("{}, {}", s3, len);

    borrowing();

    slices();
}


fn takes_ownership(some_string: String) {
    println!("{}", some_string);
}

fn makes_copy(some_integer: i32) {
    println!("{}", some_integer);
}

fn gives_ownership() -> String {
    let some_string = String::from("hello1");
    some_string
}

fn takes_and_gives_back(a_string: String) -> String {
    a_string
}

fn calculate_length(s: String) -> (String, usize) {
    let length = s.len();

    (s, length)
}

fn borrowing() {
    println!("borrowing ----------------");

    let s1 = String::from("hello");
    let (s2, len) = claculate_len2(&s1);

    println!("{}, {}, {}", s1, s2, len);

    let c = String::from("hello");
    change(&c);

    let c2: &String = &String::from("hello");
    change(c2);

    let mut s = String::from("hello");
    change2(&mut s);
    println!("{}", s);

    mutable_references();
}

fn claculate_len2(s: &String) -> (&String, usize) {
    (s, s.len())
}

fn change(some_string: &String) {
    // some_string.push_str(", world");
}

fn change2(some_string: &mut String) {
    some_string.push_str(", world");
}

fn mutable_references() {
    let mut s = String::from("hello");

    // let r1 = &mut s;
    let r2 = mutable_ref_test((&mut s));

    // println!("{}, {}", s, r2);

    let mut s = String::from("hello");
    {
        let r1 = &mut s;
        println!("{}", r1);
    }

    let r2 = &mut s;

    println!("{}", r2);
}

fn mutable_ref_test(refer_string: &mut String) -> &mut String {
    refer_string
}

fn mutable_immutable() {
    let mut s = String::from("hello");

    let r1 = &s;
    let r2 = &s;
    let r3 = &mut s;

    // println!("{}{}{}", r1, r2, r3);
}

fn dangling_reference() {
    // let reference_to_nothing = dangle();
    let reference_to_something = no_dangle();
}

// fn dangle() -> &String {
//     let s = String::from("hello");
//     &s
// }

fn no_dangle() -> String {
    let s = String::from("hello");
    s
}

fn slices() {
    let mut s = String::from("hello world");

    let word = first_word(&s[..]);
    // s.clear();
    println!("search first word: {}", word);

    let my_string_literal = "hello world";

    let word = first_word(&my_string_literal[..]);
    // my_string_literal.clear();
    println!("search first word: {}", word);

    let word = first_word(&my_string_literal);
    // my_string_literal.clear();
    println!("search first word: {}", word);


    let slice = &s[0..2];
    println!("{}", slice);

    let sclice= &s[..2];
    println!("{}", slice);

    let len = s.len();

    let slice = &s[3..len];
    println!("{}", slice);

    let slice = &s[3..];
    println!("{}", slice);
}

fn first_word(s: &str) -> &str {
    let bytes: &[u8] = s.as_bytes();

    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[0..i];
        }
    }

    &s[..]
}
