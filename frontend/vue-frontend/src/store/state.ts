import {Api} from "@/generated-api/Api";
import {Auth} from "@/generated-api/Auth";
export interface State {
    auth: Auth,
    api: Api,
    authorization_set: boolean,
}
export let state: State = {
    api: new Api(),
    authorization_set: false,
    auth: new Auth({
        baseUrl: "http://localhost:8009"
    })
}
