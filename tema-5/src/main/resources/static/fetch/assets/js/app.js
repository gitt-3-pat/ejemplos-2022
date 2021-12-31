async function getUsers() {
    let url = 'assets/data/users.json';
    try {
        let res = await fetch(url);
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}

async function getUsers2() {
    let url = 'assets/data/users2.json';
    try {
        let res = await fetch(url);
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}

async function renderUsers() {

   	let [users, users2] = await Promise.all([getUsers(), getUsers2()]);
    let html = '';

    users.map(user => {
        let htmlSegment = `<div class="user">
                             <img src="${user.profileURL}" >
                             <h2>${user.firstName} ${user.lastName}</h2>
                             <div class="email"><a href="email:${user.email}">${user.email}</a></div>
                           </div>`;
        html += htmlSegment;
    });
    
    users2.map(user => {
        let htmlSegment = `<div class="user">
                             <img src="${user.profileURL}" >
                             <h2>${user.firstName} ${user.lastName}</h2>
                             <div class="email"><a href="email:${user.email}">${user.email}</a></div>
                           </div>`;
        html += htmlSegment;
    });

    let container = document.querySelector('.container');
    container.innerHTML = html;
}

renderUsers();