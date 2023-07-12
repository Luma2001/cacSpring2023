function jwtAdapter(dataExterno){//en este caso un text recibido en useCaseLogin
    const jwt = {
        type:'Bearer',
        jwt:dataExterno
    }
    return jwt;
}