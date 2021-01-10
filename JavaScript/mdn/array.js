var arr = [];
arr[3.4] = "Oranges";
arr.push("Apple");

console.log(arr.length);
console.log(arr["length"]);
console.log(arr);

console.log(arr.hasOwnProperty(3.4)); 

var cats = ['Dusty', 'Misty', 'Twiggy'];
console.log(cats.length); // 3

cats.length = 2;
console.log(cats); // logs "Dusty,Misty" - Twiggy has been removed

cats.length = 0;
console.log(cats); // logs nothing; the cats array is empty

cats.length = 3;
console.log(cats); // [undefined, undefined, undefined]

var colors = [ 'red', 'green', 'blue'];
for (var i = 0; i < colors.length; i++ ){
	console.log(colors[i]);
}

console.log("<<< forEach >>> ");

colors.forEach(function(color){
	console.log(color);
});


console.log("<<< forEach2 >>> ");

arr = Array(4);
arr[0] = 'Red';
arr[1] = 'Green';
arr[3] = 'Blue';

arr.forEach(function(color){
	console.log(color);
});

if ( arr[2] === undefined ) { console.log('arr[2] is undefined.');};

arr = [ 'first', 'second', undefined, 'fourth'];

arr.forEach(function(element){
	console.log(element);
});


var array = ['first', 'second', , 'fourth'];
array.forEach(function(element){
	console.log(element);
});

array['test'] = "test";
console.log(array);

for(var index in array){
	console.log("index: " + index + ", " +array[index]);
}

// var divs = document.getElementsByTagName('div');
// for( var i = 0, div; div = divs[i]; i++){
// 	console.log(div);
// }

console.log("<<< concat >>>");

var myArray = new Array("1", "2", "3");
myArray = myArray.concat("a", "b", "c");
console.log(myArray);

myArray = new Array("Wind", "Rain", "Fire");
var list = myArray.join(" - ");
console.log(list);

myArray = new Array("1", "2", "3");
var last = myArray.push("5");
console.log(last);

last = myArray.pop();
console.log(last);

var first = myArray.shift();
console.log(first);

var unshift = myArray.unshift("4", "5");
console.log(unshift);
console.log(myArray);

console.log("<<< slice >>>");
myArray = ["a", "b", "c", "d", "e"];
console.log(myArray);
myArray = myArray.slice(1, 4 );
console.log(myArray);

myArray = new Array("1", "2", "3", "4", "5");
myArray.splice(1, 2, "a", "b", "c", "d");
console.log(myArray);

console.log(myArray.reverse());
console.log(myArray.sort());


myArray = ['a', 'b', 'c', 'd', 'a', 'b'];
console.log(myArray.indexOf('a'));
console.log(myArray.indexOf("a"));
console.log(myArray.indexOf('b'));
console.log(myArray.indexOf('b', 2));

console.log(myArray.lastIndexOf('b'));
console.log(myArray.lastIndexOf('b', 4));
console.log(myArray.lastIndexOf('z'));


console.log("<<< forEach >>>");
console.log(myArray);
myArray.forEach(function(element){
	console.log(element);
});

var a2 = myArray.map(function(item){
	return item.toUpperCase();
});
console.log(a2);

var a1 = ['a', 10, 'b', 20, 'c', 30];
var a2 = a1.filter(function(item){
	// console.log(typeof item);
	return typeof item == 'number';
});
console.log(a2);



function isNumber(value){
	return typeof value == 'number';
}

a1 = [1, 2, 3];
a2 = [1, '2', 3];
a3 = ['1', '2', '3'];

console.log("__ every");
console.log(a1 + " is " + a1.every(isNumber));
console.log(a2 + " is " + a2.every(isNumber));
console.log(a3 + " is " + a3.every(isNumber));
console.log("__ some");
console.log(a1 + " is " + a1.some(isNumber));
console.log(a2 + " is " + a2.some(isNumber));
console.log(a3 + " is " + a3.some(isNumber));

a1 = [10, 30, 50];
console.log("<<< reduce >>> " + a1);
var total = a1.reduce(function(first, second){
	console.log(first + ", " + second);
	return first + second;
}, 3);
console.log(total);


var a = new Array(4);
for ( i = 0; i < 4; i++){
	a[i] = new Array(4);
	for ( j = 0; j < 4; j++){
		a[i][j] = "[" + i + "," + j +"]";
	}
}

a.forEach(function(element){
	console.log(element);
});


class Person{
	constructor(name){
		this.name = name;
		this.id = Person.nextId++;
	}
}

Person.nextId = 0;
const jamie = new Person("Jamie"),
	juliet = new Person("Juliet"),
	peter = new Person("Peter"),
	jay = new Person("Jay");
arr = [jamie, juliet, peter, jay];

arr.find(p => p.id === juliet.id);
arr.find(function (p){
	return p.id === this.id
}, juliet);

const items = ["Widget", "Gadget"];
const prices = [9.95, 22.95];
const cart = items.map((x,i) => ({ name: x, price: prices[i]}));
console.log(cart);

const cards = [];
for(let suit of ['H', 'C', 'D', 'S']){
	for (let value = 1; value <= 13; value++){
		cards.push( {suit, value});
	}
}

var filteredCards = cards.filter( c => c.value === 2);
filteredCards = cards.filter( c => c.value >= 10 );


function cardToString(c){
	const suits = { 'H': '\u2665', 'C': '\u2663', 'D': '\u2666', 'S': '\u2660'};
	const values = { 1: 'A', 11:'J', 12: 'Q', 13: 'K'};

	for( let i = 2; i <= 10; i++ ){
		values[i] = i;
	}

	return values[c.value] + suits[c.suit];
}

filteredCards = cards.map(cardToString);

const words = ["Beachball", "Rodeo   ", "Angel", "Aardvark", "Xylophone", "November", 
	"Chocolate", "Papaya", "Uniform", "Joker", "Clover", "Bali"];

const alphabetical = words.reduce((a, x) => {
	if(!a[x[0]]){
		a[x[0]] = [];
	}
	a[x[0]].push(x);
	return a;
}, {});

console.log(alphabetical);


const longWords = words.reduce((a, w) => w.trim().length > 6 ? a + " " + w : a, "");

const data = [ 3.3, 5, 7.2, 12, 4, 6, 10.3];
const stats = data.reduce((a,x) => {
	a.N++;
	let delta = x - a.mean;
	a.mean += delta/a.N;
	a.M2 += delta*(x - a.mean);
	return a;
}, { N: 0, mean: 0, M2: 0});

if ( stats.N > 2 ){
	stats.variance = stats.M2 / ( stats.N - 1);
	stats.stdev = Math.sqrt(stats.variance);
}

console.log("mean & variance");

const SYM = Symbol();
const o = { a: 1, b: 2, c: 3, [SYM]: 4};
Object.keys(o).forEach(prop => console.log(`${prop}: ${o[prop]}`));

const ox = { apple:1, xochitl: 2, balloon: 3, guitar: 4, xylophone:5, };
Object.keys(ox)
	.filter(prop => prop.match(/^x/))
	.forEach(prop => console.log(`${prop}: ${ox[prop]}`));



const Car = (function(){
	const carProps = new WeakMap();

	class Car{
		constructor(make, model){
			this.make = make;
			this.model = model;
			this._userGears = [ 'P', 'N', 'R', 'D'];
			carProps.set(this, {userGear: this._userGears[0]});
			this.vin = Car.getNextVin();
		}

		static getNextVin(){
			return Car.nextVin++;
		}

		static areSimilar(car1, car2){
			return car1.make == car2.make && car1.model === car2.model;
		}

		static areSame(car1, car2){
			return car1.vin === car2.vin;
		}

		get userGear(){ return carProps.get(this).userGear; }
		set userGear(value){
			if(this._userGears.indexOf(value) < 0){
				// throw new Error(`Invalid gear: $(value}`);
				console.log(`Error!! Invalid gear: ${value}`);
			}
			carProps.get(this).userGear = value;
		}
		shift(gear){ this.userGear = gear; return this.userGear;}
	}

	Car.nextVin = 0;

	return Car;
})();


const car1 = new Car("Tesla", "S");
const car2 = new Car("Mazda", "3");
const car3 = new Car("Mazda", "3");

console.log("<<< Car >>>");
console.log(car1.shift === Car.prototype.shift);
console.log(car1.shift('D'));
console.log(car1.shift('d'));
console.log(car1.shift === car2.shift);

console.log("<<< new ar1 >>>");

car1.shift = function(gear){ this.userGear = gear.toUpperCase(); return this.userGear;}
console.log(car1.shift === Car.prototype.shift);
console.log(car1.shift === car2.shift);
console.log(car1.shift('R'));
console.log(car1.shift('n'));
console.log(car1.userGear)

console.log("<<< static method >>>");

console.log(car1.vin);
console.log(car2.vin);
console.log(car3.vin);

console.log(Car.areSimilar(car1, car2));
console.log(Car.areSimilar(car2, car3));
console.log(Car.areSame(car2, car3));
console.log(Car.areSame(car2, car2));
