fn main() {
    println!("Hello, world!");

    create(); //5.1

    example(); //5.2 구조체를 이용한 예제 프로그램
}

struct User {
    username: String,
    email: String,
    sign_in_count: u64,
    active: bool,
}

fn create() {
    let user1 = User {
        email: String::from("someone@example.com"),
        username: String::from("someusername123"),
        active: true,
        sign_in_count:1,
    };

    // user1.email = String::from("anotheremail@example.com");

    let mut user1 = User {
        email: String::from("someone@example.com"),
        username: String::from("someusername123"),
        active: true,
        sign_in_count:1,
    };

    user1.email = String::from("anotheremail@example.com");

    user1 = build_user(String::from("someone@example.com"), String::from("someusername123"));
    println!("{}", user1.email);

    user1 = build_user1(String::from("someone@example.com"), String::from("someusername123"));
    println!("{}", user1.email);

    let user2 = User {
        email: String::from("another@example.com"),
        username: String::from("anotherusername567"),
        active: user1.active,
        sign_in_count: user1.sign_in_count
    };

    let user2 = User {
        email: String::from("another@example.com"),
        username: String::from("anotherusername567"),
        ..user1
    };

    struct Color(i32, i32, i32);
    struct Point(i32, i32, i32);

    let black = Color(0, 0, 0);
    let origin = Point(0, 0, 0);

}

fn build_user(email: String, username: String) -> User {
    User {
        email: email,
        username: username,
        active: true,
        sign_in_count: 1,
    }
}

fn build_user1(email: String, username: String) -> User {
    User{
        email,
        username,
        active: true,
        sign_in_count: 1,
    }
}

fn example() {
    let length1 = 50;
    let width1 = 30;

    println!(
        "The area of the rectangle is {} square pixels.",
        area(length1, width1)
    );

    let rect1 = (50, 30);
    println!(
        "The area of the rectangle is {} square pixels.",
        area1(rect1)
    );

    let rect1 = Rectangle{ length: 50, width: 30};
    println!(
        "The area of the rectangle is {} square pixels.",
        area3(&rect1)
    );
}

fn area(length: u32, width: u32) -> u32 {
    length * width
}

fn area1(dimensions: (u32, u32)) -> u32 {
    dimensions.0 * dimensions.1
}

struct Rectangle {
    length: u32,
    width: u32,
}

fn area3(rectangle: &Rectangle) -> u32 {
    rectangle.length * rectangle.width
}