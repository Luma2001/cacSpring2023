 const url = 'http://localhost:8081';
 
 const getToken = async () => {//para poder llamar await está fc debe ser async(asincrónica)
    //pedir un token a una api
    //debugger;


       const username = document.getElementById('username').value;
       const password = document.getElementById('password').value;
    
    //utilizando caso de uso
    const loginUseCase = loginUC(loginRepository());
    const jwt = await loginUseCase(username,password);
    localStorage.setItem('jwt',JSON.stringify(jwt));
    alert(JSON.stringify(jwt));
       
 }

const findUsers = async () => {
    //pedir un token a una api
    //api/v1/auth

    const jwtInLS = localStorage.getItem('jwt');//esto es un string, no un objeto
    const jwtObj = JSON.parse(jwtInLS);//acá lo trnasformo en objeto
    
    //algo nativo de los browser para hacer peticiones asincrónicas
    console.log(`${jwtObj.type} ${jwtObj.jwt}`);
    const res = await fetch(`${url}/user`,{
        method:'GET',
        headers: {
            Authorization: `${jwtObj.type} ${jwtObj.jwt}`
        }
    });

    const usuarios = await res.json();
    document.getElementById('listado').innerHTML = UserList(usuarios);
 
    function UserList(usuarios){
        const table = ` <table class = "table">
            <thead>
                <tr>
                    <th scope = "col">username</th>
                    <th scope = "col">password</th>
                    <th scope = "col">roles</th>
                </tr>    
            </thead>
            <tbody>
                ${usuarios.map( x => UserRow(x)).join('')}
            </tbody>
        </table>`;
        return table;
    }

    function UserRow(usuario){
        return `<tr>
            <th scope ="row">${usuario.username}</th>
            <td>${usuario.password}</td>
            <td>${JSON.stringify(usuario.roles)}</td>
          </tr>`;
    }
 

    /*
        .then(response => response.json())
        .then(data => console.log(data));  
    */    
}
const btnToken = document.getElementById('btn-getToken');
btnToken.addEventListener('click',getToken);
const btnListado = document.getElementById('btn-getListado');
btnListado.addEventListener('click',findUsers);
