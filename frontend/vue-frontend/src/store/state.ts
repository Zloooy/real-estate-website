import {Api} from "@/generated-api/Api";

export const state = {
    api: new Api(),
    authorization_set: false
}

export type State = typeof state;