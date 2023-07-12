/*
CLOUSURE JAVASCRIPT -> encapsulamiento de funciones
*/

function loginUC(repository){
    
    async function exec(username,password){
    const response = await repository (username,password); //llamo la función ubicada en /login/infraestructure/fetch-apis.js
       const jwt = await response.text();  
       return jwtAdapter(jwt);
    }
    return exec;
}