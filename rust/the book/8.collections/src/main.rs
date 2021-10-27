fn main() {
    println!("Hello, world!");

    vector(); //8.1 벡터

    string(); //8.2 스트링

    hash_map(); //8.3 해쉬맵
}


fn vector() {
    let _v: Vec<i32> = Vec::new();
    let _v = vec![1, 2, 3];

    let mut v = Vec::new();
    v.push(5);
    v.push(6);
    v.push(7);
    v.push(8);

    let v = vec![1, 2, 3, 4, 5];

    let third: &i32 = &v[2];
    println!("{}", third);

    let third: Option<&i32> = v.get(2);
    println!("{}", third.unwrap());

    let v = vec![1, 2, 3, 4, 5];
    // let does_not_exist = &v[100];
    // let does_not_exist = v.get(100);
    // println!("{}", does_not_exist.unwrap());


    let mut v = vec![1, 2, 3, 4, 5];
    let first = &v[0];
    // v.push(6);
    println!("The first element is: {}", first);


    let v = vec![100, 32, 57];
    for i in &v {
        println!("{}", i)
    }

    let mut v = vec![100, 32, 57];
    for i in &mut v {
        *i += 50;
    }

    let row = vec![
        SpreadsheetCell::Int(3),
        SpreadsheetCell::Text(String::from("blue")),
        SpreadsheetCell::Float(10.12),
    ];
}

enum SpreadsheetCell {
    Int(i32),
    Float(f64),
    Text(String),
}


fn string() {
    let mut s = String::new();

    let data = "initial contents";
    let s = data.to_string();
    let s = "initial contents".to_string();

    let s = String::from("initial contents");

    let hello = String::from("السلام عليكم");
    let hello = String::from("Dobrý den");
    let hello = String::from("Hello");
    let hello = String::from("שָׁלוֹם");
    let hello = String::from("नमस्ते");
    let hello = String::from("こんにちは");
    let hello = String::from("안녕하세요");
    let hello = String::from("你好");
    let hello = String::from("Olá");
    let hello = String::from("Здравствуйте");
    let hello = String::from("Hola");


    let mut s = String::from("foo");
    s.push_str("bar");
    println!("{}", s);


    let mut s1 = String::from("foo");
    let s2 = "bar";
    s1.push_str(&s2);
    println!("s2 is {}", s2);


    let mut s = String::from("lo");
    s.push('l');
    println!("s is {}", s);


    let s1 = String::from("Hello, ");
    let s2 = String::from("world!");
    let s3 = s1 + &s2;
    // println!("{}", s1);
    println!("{}", s2);
    println!("{}", s3);


    let s1 = String::from("tic");
    let s2 = String::from("tac");
    let s3 = String::from("toe");

    let s = s1 + "-" + &s2 + "-" + &s3;
    // println!("{}", s1);
    println!("{}", s);


    let s1 = String::from("tic");
    let s2 = String::from("tac");
    let s3 = String::from("toe");

    let s = format!("{}-{}-{}", s1, s2, s3);
    println!("{}", s1);
    println!("{}", s);


    let s1 = String::from("hello");
    // let h = s1[0];
    let len = String::from("Hola").len();
    println!("{}", len);

    let len = String::from("Здравствуйте").len();
    println!("{}", len);


    let hello = "Здравствуйте";
    let s = &hello[0..4];
    println!("{}", s);
    // let s = &hello[0..1];


    for c in "नमस्ते".chars() {
        println!("{}", c);
    }

    for b in "नमस्ते".bytes() {
        println!("{}", b);
    }
}


fn hash_map() {
    use std::collections::HashMap;

    let mut scores = HashMap::new();

    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Yellow"), 50);

    let teams = vec![String::from("Blue"), String::from("Yellow")];
    let initail_scores = vec![10, 50];

    let scores: HashMap<_, _> = teams.iter().zip(initail_scores.iter()).collect();

    //소유권
    let field_name = String::from("Favorite color");
    let field_value = String::from("Blue");

    let mut map = HashMap::new();
    map.insert(field_name, field_value);
    // println!("{}:{}", field_name, field_value);
    println!("{:?}", map);


    //값 접근
    let mut scores = HashMap::new();
    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Yellow"), 50);

    let team_name = String::from("Blue");
    let score = scores.get(&team_name);
    println!("{}:{}", team_name, score.unwrap());

    for (key, value) in &scores {
        println!("{}: {}", key, value);
    }

    //해쉬맵 갱신하기
    let mut scores = HashMap::new();

    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Blue"), 25);
    println!("{:?}", scores);

    //키에 할당된 값이 없을 경우에만 삽입하기
    let mut scores = HashMap::new();
    scores.insert(String::from("Blue"), 10);

    scores.entry(String::from("Yellow")).or_insert(50);
    scores.entry(String::from("Blue")).or_insert(50);
    println!("{:?}", scores);

    //예전 값을 기초로 값을 갱신하기
    let text = "hello world wonderful world";
    let mut map = HashMap::new();

    for word in text.split_whitespace() {
        let count = map.entry(word).or_insert(0);
        *count += 1;
    }
    println!("{:?}", map);

    exercise();

}


fn exercise(){
    use std::collections::HashMap;

//정수 리스트가 주어졌을 때, 벡터를 이용하여 이 리스트의 평균값(mean, average),
//중간값(median, 정렬했을 때 가장 가운데 위치한 값),
//그리고 최빈값(mode, 가장 많이 발생한 값; 해쉬맵이 여기서 도움이 될 것입니다)를 반환해보세요.

    let mut numbers = vec![1, 12, 56, 2, 5, 23, 5, 9, 17, 19, 8, 2, 3, 5, 8, 1, 23, 7,];

    let mut max = 0;
    let mut min = 0;
    // let mut mean = 0;
    let mut sum = 0;
    let mut mode = HashMap::new();

    numbers.sort();
    println!("numbers: {:?}", numbers);

    let len = numbers.len();
    let median = numbers[len/2];

    for number in numbers {
        sum += number;
        let count = mode.entry(number).or_insert(0);
        *count += 1;
    }

    let mean = sum / len;

    println!("mean: {}", mean);
    println!("median: {}", median);

    let mut mode_key = 0;
    for (key, value) in &mode {
        if max < *value {
            mode_key = *key;
            max = *value;
        }
    }

    println!("{:?}", mode);
    println!("mode  {}", mode_key);
}