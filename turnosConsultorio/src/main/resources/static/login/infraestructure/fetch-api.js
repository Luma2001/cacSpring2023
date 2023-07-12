function loginRepository(){//creo un clousure para poder pasar la fc async como una dependencia de caso de uso
    async function login(username, password){ 
        //NO NECESITA SABER QUE EXISTE UN HTML
        //RECIBE LOS PARAMETROS DESDE AFUERA
        //SE LOS PASA

        const data = {
            username : username,
            password : password
        }

        //api, algo nativo de los browser para hacer peticiones asincrónicas
        //await: es lo que se java promesa en javascript

    return await fetch(`${url}/api/v1/auth/authenticate`,{
        method:"POST",
        headers:{
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    })

    //lo comento porque no lo quiero resolver la petición asincrónica acá, 
    //debo crear un fc asincrónica, para que me guarde la resolucion para más adelante
    //y cuando termine la retornar
    /*
        .then(response => response.text()) //lo que captura lo transforma en texto
        .then(data => console.log(data));  //para que muestre la info por consola*/
    }

    return login;
}