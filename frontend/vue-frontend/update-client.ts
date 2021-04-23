// @ts-ignore
//const fetch = require('node-fetch');
const { generateApi } = require('swagger-typescript-api');
const waitOn = require('wait-on');
const path = require('path')
const fs = require('fs');
waitOn({
    resources: ["http://server:8080/v3/api-docs"],
    delay: 100
},).then(()=>
generateApi({
    name: "Api.ts",
    output: path.resolve(process.cwd(), "./src/generated-api/"),
    url: "http://server:8080/v3/api-docs/",
    input: path.resolve(process.cwd(), "./api.json"),
    httpClientType: "fetch",
    modular:true,
    cleanOutput: true,
})
    .then(({files, configuration})=>{
        files.forEach(
            ({content, name}) => {
            fs.writeFile(path.resolve(process.cwd(), `./src/generated-api/${name}`), content, ()=>{});
        }
        );
    })
    .catch(e => console.error(e))
)
    .catch(err=>console.error(err));