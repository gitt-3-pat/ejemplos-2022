//Browser Object Model
//recargar página
window.location.reload()
//navegar
window.location.href = "https://google.es";
//hitórico
window.history
//ir atrás
window.history.back()
window.history.go(-1)
//actualizar
window.history.go()
//ir adelante
window.history.forward()
//Objeto navigator -> información del navegadores
window.navigator
//Manipular la screen
//infor de la pantalla actual
window.screen
window.moveTo(0, 0);
window.resizeTo(window.screen.availWidth, window.screen.availHeight);


//DOM
//obtener un elemento con id específico
document.getElementById("input-password")
//obtener Formularios
document.getElementsByTagName("form")
//obtener por Classes
document.getElementsByClassName("card")
//obteener por Nombre
document.getElementsByName("myinput")

//Creación de elementos
const div = document.createElement("div");      // Creamos un <div></div>
const span = document.createElement("span");    // Creamos un <span></span>
const img = document.createElement("img");      // Creamos un <img>
const comment = document.createComment("Comentario"); // <!--Comentario-->
const text = document.createTextNode("Hola");         // Nodo de texto: 'hola'
const div = document.createElement("div");
div.textContent = "Elemento 1";
div.id = "page";          // <div id="page"></div>
div.className = "data";   // <div id="page" class="data"></div>
div.style = "color: red"; // <div id="page" class="data" style="color: red"></div>
const div2 = div.cloneNode();   // Ahora SÍ estamos clonando
div2.textContent = "Elemento 2";
div.textContent;  // 'Elemento 1'

//Insercción de contenido
const data = document.querySelector(".data");
data.innerHTML = "<h1>Tema 1</h1>";
data.textContent;   // "Tema 1"
data.innerHTML;     // "<h1>Tema 1</h1>"
data.outerHTML;     // "<div class="data"><h1>Tema 1</h1></div>"
const div = document.createElement("div");  // <div></div>
div.textContent = "Ejemplo";                // <div>Ejemplo</div>

const app = document.querySelector("#app"); // <div id="app">App</div>
app.insertAdjacentElement("beforebegin", div);
// Opción 1: <div>Ejemplo</div> <div id="app">App</div>
app.insertAdjacentElement("afterbegin", div);
// Opción 2: <div id="app"> <div>Ejemplo</div> App</div>
app.insertAdjacentElement("beforeend", div);
// Opción 3: <div id="app">App <div>Ejemplo</div> </div>
app.insertAdjacentElement("afterend", div);
// Opción 4: <div id="app">App</div> <div>Ejemplo</div>

const div = document.querySelector(".item:nth-child(2)");   // <div class="item">2</div>
document.body.removeChild(div); // Desconecta el segundo .item

const div = document.querySelector(".item:nth-child(2)");
const newnode = document.createElement("div");
newnode.textContent = "DOS";
document.body.replaceChild(newnode, div);

<div id="page" class="info data dark" data-number="5"></div>
//manipulación de clases de elementos
const div = document.querySelector(".info");
// Obtener clases CSS
div.className;              // "info data dark"
div.getAttribute("class");  // "info data dark"
// Modificar clases CSS
div.className = "elemento brillo tema-oscuro";
div.setAttribute("class", "elemento brillo tema-oscuro");

<div id="page" class="info data dark" data-number="5"></div>
const div = document.querySelector("#page");
div.classList; // ["info", "data", "dark"]
div.classList.add("uno", "dos"); // No devuelve nada.
div.classList; // ["info", "data", "dark", "uno", "dos"]
div.classList.remove("uno", "dos"); // No devuelve nada.
div.classList; // ["info", "data", "dark"]
