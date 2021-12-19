//Usando el Objeto console

// console.log() method
console.log('abc');  
console.log(1); 
console.log(true); 
console.log(null); 
console.log(undefined);  
console.log([1, 2, 3, 4]); // array inside log 
console.log({a:1, b:2, c:3}); // object inside log 

//error
console.error('This is a simple error');

//waring
console.warn('This is a warning.'); 

//console.clear();

//console.time() and console.timeEnd() method  
console.time('abc'); 
 let fun =  function(){ 
     console.log('fun is running'); 
 } 
 let fun2 = function(){ 
     console.log('fun2 is running..'); 
 } 
 fun(); // calling fun(); 
 fun2(); // calling fun2(); 
console.timeEnd('abc'); 

// console.table() method 
console.table({'a':1, 'b':2}); 

// console.count() method 
for(let i=0;i<5;i++){ 
    console.count(i); 
} 

// console.group() and console.groupEnd() method 
console.group('simple'); 
  console.warn('warning!'); 
  console.error('error here'); 
  console.log('letsUpgrade'); 
console.groupEnd('simple'); 
console.log('new section');

// CSS Example  
const spacing = '10px'; 
const styles =  
      `padding: ${spacing}; background-color: white; color: green; font-style:  
       italic; border: 1px solid black; font-size: 2em;`; 
console.log('%c Hola clase', styles);

//information 
var info = { firstname : "Lest", lastname : "Upgrade" };
console.info(info);             

//create a collapsed group of massage in console
console.log("Hello world!");
console.groupCollapsed();
console.log("Hello again, this time inside a collapsed group!");
console.groupEnd();
console.log("Break the Group collapsed ");
