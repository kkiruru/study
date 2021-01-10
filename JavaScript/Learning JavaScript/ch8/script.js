const arr1 = [1,2,3];
const arr2 = ["one", 2, "three"];
const arr3 = [[1,2,3], ["one", 2, "three"]];
const arr4 = [
	{name: "Fred", type: "object", luckyNumbers : [5, 7, 13]},
	[
		{name: "Susan", type: "object"},
		{name: "Anthony", type: "object"},
	],
	1,
	function() { return "arrays can contain functions too";},
	"three",
];

// console.log(arr4);

const arr = ["b", "c", "d"];
arr.push("e");
console.log(arr);

arr.pop();
console.log(arr);

arr.unshift("a");
console.log(arr);

arr.shift();
console.log(arr);

const arr5 = [ 1, 2, 3];
let arr6 = arr5.concat( 4, 5, 6 );
console.log("arr5: " + arr5);
console.log("arr6: " + arr6);

arr6 = arr5.concat([4, 5, 1]);
console.log("arr6: " + arr6);

const arr7 = [0, 1, 2, 3, 4, 5, 6, 7];
console.log("arr7: "+arr7);
console.log("slice(2, 4): " + arr7.slice(2, 4));
console.log("slice(2): " + arr7.slice(2));
console.log("slice(-2): " + arr7.slice(-2));
console.log("slice(-2, 4): " + arr7.slice(-2, 4));
console.log("slice(-2, 8): " + arr7.slice(-2, 8));
console.log("slice(-10): " + arr7.slice(-10));
console.log("slice(2): " + arr7.slice(2));
console.log("slice(-4): " + arr7.slice(-4));
console.log("slice(2, -3): " + arr7.slice(2, 3));


console.log("splice");

const arr8 = [1, 5, 7];
arr8.splice(1, 0, 2, 3, 4);
console.log(arr8);

console.log("copyWithin");

const arr9 = [1, 2, 3, 4, 5, 6, 7];
arr9.copyWithin(1, -5, -2);

console.log(arr9);


console.log("fill");

const arr10 = new Array(5).fill(1);

arr10.fill("a");
arr10.fill("b", 1);
arr10.fill("c", 2, 4);
arr10.fill(5.5, -4);
arr10.fill(0, -3, -1)
console.log(arr10);

console.log("reverse");
const arr11 = [1, 2, 3, 4, 5];
arr11.reverse();
console.log(arr11);

arr11.sort();
console.log(arr11);


console.log("sort");

const arr12 = [{name: "Suzanne"}, {name: "Jim"}, {name: "Trevor"}, {name: "Amanda"}];
console.log(arr12);

arr12.sort((a,b) => a.name > b.name);
console.log(arr12);

arr12.sort((a,b) => a.name[1] < b.name[1]);
console.log(arr12);

var arr13 = Array();
arr13[0] = "Casey Jones";
arr13[1.2] = "Phil Lesh";
console.log(arr13);
