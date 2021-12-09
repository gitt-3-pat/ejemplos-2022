//Las Promises tiene 3 estados: pending, fullfilled(con respuesta), rejected (con respuesta errónea/rechazada)
//son objetos de naturaleza asíncrona

//Al construir una Promise le pasamos como argumento una function con dos parámetros asociados a los futuros estados
//estos estados son, en este orden, fullfilled y rejected

let promise = new Promise(function(resolver,rechazar){
  //setTimeout para hacer un sleep de 2 segundos y provocar un fullfilled()
  setTimeout(function(){
    resolver("Mensaje", {"user":"jacvier"}, true)
  }, 2000);
});

//suscripción a la promesa
promise.then(function(response, object ,bool){
  console.log("Promesa completada con exito")
  console.log("Mensaje de respuesta " + response);
  console.log("Objeto de respuesta " + object);
  console.log("Boolean de respuesta " + bool);
})
//EL equivalente a la suscripción anterior con notación Arrow Function
//Para más información: https://developer.mozilla.org/es/docs/Web/JavaScript/Referencia/Funciones/Arrow_functions
promise.then((response, object ,bool) => {
  console.log("Promesa completada con exito")
  console.log("Mensaje de respuesta " + response);
  console.log("Objeto de respuesta " + object);
  console.log("Boolean de respuesta " + bool);
});
//provocamos error
let promise = new Promise((resolver,rechazar) => {
  setTimeout(function(){
    rechazar("ERROR 401 HTTP")
  }, 2000);
});

//suscribimos al catch para procesar el estado rejected de la Promise
promise.catch((errorMessage) => {
  console.error( errorMessage);
});

//Las funciones asíncronas no bloquean la ejecución del código/programa principal
async function processBatch(){
  //TO-DO time consuming task
}
//Al invocar a la función processBatch() desde cualquier sitio, no se bloqueará,y no esperará a que finalice


//Funciones generadoras
//Funcion generador con while -> siempre devolverá un valor (infinitas veces)
function* assignId(){
  let j=0;
  while(true)
    yield j++;
}
//Funcion generador con for -> solo devolverá valores 3 veces, luego devolverá undefined
function* assignIdFrom0_to_2(){
  for(j=0;j<3;j++){
    yield j;
  }
}

//para usar los generadores tenemos que guardar la referencia, creando variables
// e invocando a la función generadora, lo que nos devuelve un objeto Generator
//que después podemos invocar con next() para conseguir el siguiente valor
var idGeneratorInf = assignId();
var idGeneratorFor = assignIdFrom0_to_2();

//Este for nos devolverá siempre un ID por consola
for(i=5;i>=0;i--){
  console.log("El próximo ID es " + idGenerator.next().value)
}
//Este for nos devolverá 3 IDs y luego undefined por consola
for(i=5;i>=0;i--){
  console.log("El próximo ID es " + idGeneratorFor.next().value)
}
