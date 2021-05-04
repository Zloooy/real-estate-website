
import {MutationTree} from "vuex";
import {Api} from "@/generated-api/Api";
import {State, state} from "@/store/state";
import {City, Complex, ComplexQuery, District, Metro} from "@/generated-api/data-contracts";
import {compareObjects} from "@/utils";

export type Mutations<S = State> = {
    SET_TOKEN(state: S, payload: string): void,
    SET_CITY(state: S, payload: City),
    SET_CITIES(state: S, payload: City[])
    SET_COMPLEXES(state: S, payload: Complex[])
    SET_COMPLEX_CATEGORY(state: S, payload: ComplexQuery['estateCategory'])
    SET_SORT(state: S, payload: string)
    SET_METROS(state: S, payload: Metro[])
    SET_DISTRICTS(state: S, payload: District[])
    SET_AMOUNT_OF_ROOMS(state: S, payload: number)
    SET_COMPLEX_PAGE(state: S, payload: number)
    SET_COMPLEX_SIZE(state: S, payload: number)
    SET_METRO(state: S, payload: Metro)
    SET_DISTRICT(state: S, payload: District)
    SET_MIN_COMPLEX_PRICE(state: S, payload: number)
    SET_MAX_COMPLEX_PRICE(state: S, payload: number)
    SET_COMPLEX_DELIVERY_DATE(state: S, payload: string | undefined)
}

export const mutations: MutationTree<State> & Mutations = {
    SET_COMPLEX_DELIVERY_DATE(state: State, payload: string | undefined) {
        state.complexSearchParamsChanged = !compareObjects(state.complexDeliveryDate, payload);
        state.complexDeliveryDate = payload;
    },
    SET_MIN_COMPLEX_PRICE(state: State, payload: number) {
        state.complexSearchParamsChanged = !compareObjects(state.minComplexPrice, payload);
        state.minComplexPrice = payload;
    },
    SET_MAX_COMPLEX_PRICE(state: State, payload: number) {
        state.complexSearchParamsChanged = !compareObjects(state.maxComplexPrice, payload);
        state.maxComplexPrice = payload;
    },
    SET_SORT(state: State, payload: string) {
        state.complexSearchParamsChanged = !compareObjects(state.sort, payload);
        state.sort = payload;
    },
    SET_CITIES(state: State, payload: City[]) {
        state.cities = payload;
    },
    SET_TOKEN(state: State, payload: string) {
        state.api = new Api({
            baseUrl: "http://localhost:8009",
            baseApiParams: {
                headers: {
                    "Authorization": `Bearer ${payload}`
                },
                format: "json"
            }
        });
        state.authorization_set = true;
    },
    SET_METROS(state: State, payload: Metro[]) {
        state.metros = payload;
    },
    SET_DISTRICTS(state: State, payload: District[]) {
        state.districts = payload;
    },
    SET_COMPLEXES(state: State, payload: Complex[]){
        state.complexes = payload;
    },
    SET_CITY(state: State, payload: City){
        state.complexSearchParamsChanged = !compareObjects(state.city, payload);
        state.city = payload;
    },
    SET_COMPLEX_CATEGORY(state: State, payload: ComplexQuery["estateCategory"]) {
        state.complexSearchParamsChanged = !compareObjects(state.complex_category, payload);
        state.complex_category = payload;
        },
    SET_AMOUNT_OF_ROOMS(state: State, payload: number){
        state.complexSearchParamsChanged = !compareObjects(state.amountOfRooms, payload);
        state.amountOfRooms = payload;
        },
    SET_COMPLEX_PAGE(state: State, payload: number) {
        state.complexPage = payload;
    },
    SET_COMPLEX_SIZE(state: State, payload: number) {
        state.complexSize = payload;
    },
    SET_METRO(state: State, payload: Metro) {
        state.complexSearchParamsChanged = !compareObjects(state.metro, payload);
        state.metro = payload;
    },
    SET_DISTRICT(state: State, payload: District) {
        state.complexSearchParamsChanged = !compareObjects(state.district, payload);
        state.district = payload;
    }
}