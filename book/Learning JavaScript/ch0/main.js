$(document).ready(function(){
    'use strict';
    paper.install(window)
    paper.setup(document.getElementById('mainCanvas'));

    //TODO
    // var c = Shape.Circle(200, 200, 50);
    // c.fillColor = 'green';

	var c
	for(var x = 25; x < 400; x +=50){
		for( var y = 25; y < 400; y+=50){
			c = Shape.Circle(x,y,20);
			c.fillColor = 'green'
		}
	}

	var tool = new Tool();

	var circle = Shape.Circle(200, 200, 80);
	circle.fillColor = 'black';
	var text = new PointText(200, 200);
	text.justification = 'center';
	text.fillColor = 'white';
	text.fontSize = 20;
	text.content = 'hello world';

	tool.onMouseDown = function(event){
		// var c = Shape.Circle(event.point.x, event.point.y, 20);

		var c = Shape.Circle(event.point, 20);
		c.fillColor = 'green';
	};

	paper.view.draw();
	console.log('main.js loaded');
	


})


const sam1 = {
	name: 'Sam',
	age: 4,

};

const sam2 = { name: 'Sam', age:4};

const sam3 = {
	name: 'Sam',
	classification:{
		kingdom: 'Anamalia',
		phylum: 'Choradata',
		class:'Mamalia',
		order:'Carnivoria',
		family:'Felidae',
		subfamily:'Felinae',
		genus:'Felis',
		species:'catus',
	},
};

sam3.classification.family;
sam3["classification"].family;
sam3.classification["family"];
sam3["classification"]["family"];

sam3.speak = function(){ return "Meow!";};

sam3.speak();

delete sam3.classification;
delete sam3.speak;

const s = "hello";
s.toUpperCase();
s.rating = 3;
s.rating;


const a1 = [1,2,3,4];
const a2 = [1, 'two', 3, null];
const a3 = [
	"What the hammer? What the chain?",
	"In what furnace was thy brain?",
	"What the anvil? What dread grasp",
	"Dare its deadly terrors clasp?",
];
const a4 = [
	{name: "Ruby", hardness: 9},
	{name: "Diamond", hardness:10},
	{name:"Topaz", hardness:8},
];
const a5 = [
	[1,3,5],
	[2,4,6],
];

const arr = ['a', 'b', 'c']

arr.length;

arr[0];
arr[arr.length -1];


const now = new Date();
now;

const halloween = new Date(2016, 9, 3);
const halloweenParty = new Date(2016, 9, 31, 19, 0);

halloweenParty.getFullYear();
halloweenParty.getMonth();
halloweenParty.getDate();
halloweenParty.getDay();
halloweenParty.getHours();
halloweenParty.getMinutes();
halloweenParty.getSeconds();
halloweenParty.getMilliseconds();

const email = /\b[a-z0-9._-]+@[a-z_-]+(?:\.[a-z]+)+\b/;
const phone = /(:?\+1)?(:?\d{3}\)\s?|\d{3}[\s-]?)\d{3}[\s-]?\d{4}/;

const numStr = "33.3";
const num = Number(numStr);

const a = parseInt("16 volts", 10);
const b = parseInt("3a", 16);
const c = parseFloat("15.5 kph");

const d = new Date();
const ts = d.valueOf();

const bool = true;
const n = bool?1:0;

const n1 = 33.5;
n1;

const s1 = n1.toString();
s1;

const arr1 = [1, true, "hello"];
arr1.toString();

const n2 = 0;
const b1 = !!n2;
const b2 = Boolean(n2);

function change(a){
	a = 5;
}

let a6 = 3;
change(a6);
console.log(a6);

let o = {a:1};
let p = o;
o.a = 2
console.log(p);

o = {a:3}
o.a = 5
console.log(p);


function change_o(o){
	o.a = 999;
}

let o2 = {a:1};
change_o(o2);
console.log(o2);

A();


var A = function(){
	console.log("2");
}

// function A(){
// 	console.log("1");
// }
