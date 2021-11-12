fn main() {
    println!("Hello, world!");

    duplicate();

    generic_type(); //10.1 제네릭 데이터 타입

    generic_in_struct();

    trait_defining(); //10.2 트레잇: 공유 동작을 정의하기

    lifetime(); //10.3 라이프타임을 이용한 참조자 유효화
}


fn duplicate() {
    let numbers = vec![34, 50, 25, 100, 65];

    let mut largest = numbers[0];

    for number in numbers {
        if number > largest {
            largest = number;
        }
    }

    println!("The largest number is {}", largest);

    let numbers = vec![102, 34, 6000, 89, 54, 2, 43, 8];
    // let mut largest = numbers[0];

    // for number in numbers {
    //     if number > largest {
    //         largest = number;
    //     }
    // }
    // println!("The largest number is {}", largest);

    let result = simplify(&numbers);

    println!("The largest number is {}", result);
}

fn simplify(list: &[i32]) -> i32 {
    let mut largest = list[0];

    for &item in list.iter() {
        if item > largest {
            largest = item;
        }
    }

    largest
}


fn generic_type() {
    let numbers = vec![34, 50, 25, 100, 65];

    let result = largest_i32(&numbers);
    println!("The largest number is {}", result);

    let chars = vec!['y', 'm', 'a', 'q'];

    let result = largest_char(&chars);
    println!("The largest char is {}", result);


    // let numbers = vec![34, 50, 25, 100, 65];
    // let result = largest(&numbers);
    // println!("The largest number is {}", result);

    // let chars = vec!['y', 'm', 'a', 'q'];
    // let result = largest(&chars);
    // println!("The largest char is {}", result);

}

fn largest_i32(list: &[i32]) -> i32 {
    let mut largest = list[0];

    for &item in list.iter() {
        if item > largest {
            largest = item;
        }
    }

    largest
}

fn largest_char(list: &[char]) -> char {
    let mut largest = list[0];

    for &item in list.iter() {
        if item > largest {
            largest = item;
        }
    }

    largest
}

// fn largest<T>(list: &[T]) -> T {
//     let mut largest = list[0];
//     for &item in list.iter() {
//         if item > largest {
//             largest = item;
//         }
//     }

//     largest
// }

struct Point<T> {
    x: T,
    y: T,
}

fn generic_in_struct() {
    let integer = Point { x: 5, y: 10 };
    let float = Point { x: 1.0, y: 4.0 };

    // let wont_work = Point { x:5, y: 4.0 };

    let both_integer = UnionPoint { x: 5, y: 10 };
    let both_float = UnionPoint { x: 1.0, y: 4.0 };
    let integer_and_float = UnionPoint { x: 5, y: 4.0 };

    let p = Point { x: 5, y: 10 };
    println!("p.x = {}", p.x());
    println!("p = {:?}", p.point());

    let p1 = UnionPoint{ x: 5, y: 10.4 };
    let p2 = UnionPoint{ x: "Hello", y: 'c'};

    let p3 = p1.mixup(p2);
    println!("p3.x = {}, p3.y = {}", p3.x, p3.y);


    let integer = Some(5);
    let float = Some(5.0);


}

struct UnionPoint<T, U> {
    x: T,
    y: U,
}

impl<T> Point<T> {
    fn x(&self) -> &T {
        &self.x
    }
    fn point(&self) -> (&T, &T) {
        return (&self.x, &self.y);
    }
}

impl<T, U> UnionPoint<T, U> {
    fn mixup<V, W>(self, other: UnionPoint<V, W>) -> UnionPoint<T, W> {
        UnionPoint {
            x: self.x,
            y: other.y,
        }
    }
}


fn trait_defining() {
    let tweet = Tweet {
        username: String::from("horse_ebooks"),
        content: String::from("of course, as you probably alerady know, people"),
        reply: false,
        retweet: false,
    };
    println!("1 new tweet: {}", tweet.summary());


    let article = AnyArticle{};
    println!("default summary: {}", article.summary());


    let numbers = vec![34, 50, 25, 100, 65];

    let result = largest(&numbers);
    println!("The largest number is {}", result);

    let chars = vec!['y', 'm', 'a', 'q'];

    let result = largest((&chars));
    println!("The largest char is {}", result);
}

pub trait Summarizable {
    fn author_summary(&self) -> String;

    // fn summary(&self) -> String;
    fn summary(&self) -> String {
        format!("(Read more from {}...)", self.author_summary())
    }
}


pub struct NewsArticle {
    pub headlione: String,
    pub location: String,
    pub author: String,
    pub content: String,
}

impl Summarizable for NewsArticle {
    // fn summary(&self) -> String {
    //     format!("{}, by {} ({})", self.headlione, self.author, self.location)
    // }

    fn author_summary(&self) -> String {
        format!("{}", self.author)
    }
}

pub struct Tweet {
    pub username: String,
    pub content: String,
    pub reply: bool,
    pub retweet: bool,
}

impl Summarizable for Tweet {
    // fn summary(&self) -> String {
    //     format!("{}: {}", self.username, self.content)
    // }

    fn author_summary(&self) -> String {
        format!("@{}", self.username)
    }
}

struct AnyArticle {

}

impl Summarizable for AnyArticle {
    fn author_summary(&self) -> String {
        String::from("{}")
    }
}

pub fn notify<T: Summarizable>(item: T) {
    println!("Breaking news! {}", item.summary());
}

// fn some_function<T: Display + Clone, U: Clone + Debug>(t: T, u: U) -> i32 {

// }

// fn some_function2<T, U>(t: T, u: U) -> i32
//     where T: Display + Clone,
//         U: Clone + Debug
// {

// }


use std::cmp::PartialOrd;

fn largest<T: PartialOrd + Copy>(list: &[T]) -> T {
    let mut largest = list[0];
    for &item in list.iter() {
        if item > largest {
            largest = item;
        }
    }

    largest
}


fn lifetime() {

    let r;
    // {
        let x = 5;
        r = &x;
    // }

    println!("r: {}", r);


    let result;
    let string1 = String::from("long string is long");
    {
        let string2 = "xyz";
        // let string2 = String::from("xyz");
        result = longest(string1.as_str(), string2);
    }
    println!("The longest string is {}", result);


    let novel = String::from("Call me Ishmael. Some years ago...");
    let first_sentence = novel.split('.')
                            .next()
                            .expect("Could not fina a '.'");
    let i = ImportantExcerpt {  part: first_sentence };

    println!("{}", i.part);

    let s: &'static str = "I have a static lifetime.";
}


fn longest<'a>(x: &'a str, y: &'a str) -> &'a str {
    if x.len() > y.len() {
        x
    } else {
        y
    }
}

// fn longest2<'a>(x: &str, y: &str) -> &'a str {
//     let result = String::from("really long string");
//     result.as_str()
// }

struct ImportantExcerpt<'a> {
    part: &'a str,
}


fn first_word(s: &str) -> &str {
    let bytes = s.as_bytes();

    for(i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[0..i];
        }
    }

    &s[..]
}

impl<'a> ImportantExcerpt<'a> {
    fn level(&self) -> i32 {
        3
    }
}

impl <'a> ImportantExcerpt<'a> {
    fn announce_and_return_part(&self, announcement: &str) -> &str {
        println!("Attention please: {}", announcement);
        self.part
    }
}


use std::fmt::Display;

fn longest_with_an_announcement<'a, T>(x: &'a str, y: &'a str, ann: T) -> &'a str
    where T: Display {
    println!("Announcement! {}", ann);
    if x.len() > y.len() {
        x
    } else {
        y
    }
}