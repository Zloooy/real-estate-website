export const compareObjects = (o1: any, o2: any): boolean=> {
    //console.debug(`comparing ${JSON.stringify(o1)} and ${JSON.stringify(o2)}`);
    let result: boolean = false;
    if (o1==null || typeof o1 !== "object" || o2 == null || typeof o2 == "undefined")
    {
        //console.debug("compared values");
        result = o1==o2;
    }
    else if(Array.isArray(o1)){
        //console.debug("compared arrays");
        if (!Array.isArray(o2)){
            result = false;
        }
        if (o1.length !== o2.length){
            result = false;
        }
        result = Array.from(Object.keys(o1)).map(k=>compareObjects(o1[k],o2[k])).reduce((x,y)=>x&&y);
    }
    else {
        //console.debug("compared objects");
        const keys: string[] = Object.keys(o1).sort();
        if (keys.length !== Object.keys(o2).length) {
            result = false;
        }
        result = keys.map(k => o1[k] == o2[k]).reduce((x, y) => x && y);
    }
    //console.debug("returning ", result)
    return result;
}
