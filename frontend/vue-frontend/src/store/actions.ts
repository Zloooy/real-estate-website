import {Mutations} from "@/store/mutations";
import {ActionContext, ActionTree} from "vuex";
import {State} from "@/store/state";
import {Article, City, Complex, District, Flat, Metro, Request, Tariff, UserDto} from "@/generated-api/data-contracts";


type AugmentActionContext = {
    commit<K extends keyof Mutations>(
        key: K,
        payload: Parameters<Mutations[K]>[1]
    ): ReturnType<Mutations[K]>
} & Omit<ActionContext<State, State>, 'commit'>

export interface Actions {
    GET_CITIES({ commit }: AugmentActionContext,
    payload: null): Promise<City[]>,
    GET_CITIES_REDACTOR({ commit }: AugmentActionContext,
    payload: null): Promise<City[]>,
    GET_ADVERTIZED_COMPLEXES({ commit }: AugmentActionContext,
               payload: void): Promise<void>
    GET_SEARCHED_COMPLEXES({ commit }: AugmentActionContext,
                             payload: void): Promise<void>
    GET_METROS({ commit }: AugmentActionContext,
               payload: void): Promise<Metro[]>,
    GET_METROS_REDACTOR({ commit }: AugmentActionContext,
               payload: number): Promise<Metro[]>
    GET_DISTRICTS({ commit }: AugmentActionContext,
               payload: void): Promise<District[]>

    GET_DISTRICTS_REDACTOR({ commit }: AugmentActionContext,
               payload: number): Promise<District[]>

    GET_COMPLEX({ commit }: AugmentActionContext,
                  payload: number): Promise<Complex>
    GET_COMPLEX_FLATS({commit, state}: AugmentActionContext,
                      payload:number): Promise<Flat[]>,
    GET_FLAT({commit, state}: AugmentActionContext, //1
                      payload:number): Promise<Flat>,
    GET_AUTH_TOKEN({commit, state}: AugmentActionContext, payload: Request ): Promise<void>,
    GET_USERS({commit, state}: AugmentActionContext, payload: void): Promise<void>,
    GET_USER_ROLES({commit, state}: AugmentActionContext, payload: void): Promise<void>
    SAVE_USER({state, commit} : AugmentActionContext, payload: UserDto) : Promise<void>
    CREATE_USER({state, commit} : AugmentActionContext, payload: UserDto) : Promise<void>
    GET_TARIFFS({state, commit}: AugmentActionContext, payload: void) : Promise<void>,
    GET_ARTICLES({state, commit}: AugmentActionContext, payload: void) : Promise<void>
    GET_ARTICLE({state, commit}: AugmentActionContext, payload: number) : Promise<void>
    GET_AUTHORITIES({state, commit}: AugmentActionContext, payload: void): Promise<void>,
    LOGOUT({state, commit}: AugmentActionContext, payload: void): Promise<void>,
    DELETE_COMPLEX({state, commit}: AugmentActionContext, payload: number): Promise<void>,
    DELETE_FLAT({state, commit}: AugmentActionContext, payload: number): Promise<void>,
    CREATE_TARIFF({state, commit}: AugmentActionContext, payload: Tariff): Promise<void>
    EDIT_TARIFF({state, commit}: AugmentActionContext, payload: Tariff): Promise<void>
    EDIT_ARTICLE({state, commit}: AugmentActionContext, payload: Article): Promise<void>,
    CREATE_ARTICLE({state, commit}: AugmentActionContext, payload: Article): Promise<void>
    DELETE_ARTICLE({state, commit}: AugmentActionContext, payload: number): Promise<void>
}
export const actions: ActionTree<State, State> & Actions = {
    DELETE_ARTICLE({state, commit, dispatch}: AugmentActionContext, payload: number): Promise<void> {
        return state.api.deleteArticleByIdUsingDelete(payload)
            .then(()=>commit('SET_ARTICLE_PAGE', 0))
            .then(()=>dispatch('GET_ARTICLES', undefined));
    },
    CREATE_ARTICLE({state, commit}: AugmentActionContext, payload: Article): Promise<void> {
        return state.api.createArticleUsingPost(payload)
            .then(resp=>resp.data)
            .then(d=>commit('SET_CREATION_RESPONSE', d));
    }, EDIT_ARTICLE({state, commit, dispatch}: AugmentActionContext, payload: Article): Promise<void> {
        return state.api.editArticleByIdUsingPost(payload.id || 20, payload)
            .then(()=>undefined)
            //.then(()=>dispatch(''));
    },
    CREATE_TARIFF({state, commit, dispatch}: AugmentActionContext, payload: Tariff): Promise<void> {
        return state.api.createNewTariffUsingPost(payload)
            .then(()=>dispatch('GET_TARIFFS', undefined));
    },
    EDIT_TARIFF({state, commit, dispatch}: AugmentActionContext, payload: Tariff): Promise<void> {
        // @ts-ignore
        return state.api.updateTariffUsingPost(payload?.id | 30, payload)
            .then(()=>dispatch('GET_TARIFFS', undefined));
    },
    DELETE_COMPLEX({state, commit}: AugmentActionContext, payload: number): Promise<void> {
        return state.api.deleteComplexUsingDelete(payload)
            .then(r=>r.data)
            .then(()=>commit('SET_COMPLEX', undefined));
    },
    DELETE_FLAT({state, commit, dispatch}: AugmentActionContext, payload: number): Promise<void> {
        return state.api.deleteFlatUsingDelete(payload)
            .then(()=>commit('SET_FLAT', undefined))
            .then(()=>dispatch('GET_COMPLEX_FLATS', state.complex?.id));
    },
    LOGOUT({state, commit}: AugmentActionContext, payload: void): Promise<void> {
        return Promise.resolve(undefined)
            .then(()=>commit('SET_TOKEN', null))
            .then(()=>commit('SET_AUTHORITIES', []));
    },
    GET_AUTHORITIES({state, commit}: AugmentActionContext, payload: void): Promise<void> {
        return state.api.getUserAuthoritiesUsingPost()
            .then(response=>response.data)
            .catch(()=>[] as string[])
            .then((d)=>commit('SET_AUTHORITIES', d));
    },
    GET_ARTICLE({state, commit}: AugmentActionContext, payload: number): Promise<void> {
        return state.public_api.findArticleByIdUsingGet(payload)
            .then(response=>response.data)
            .then(data=>{
                commit('SET_ARTICLE', data);
            });
    },
    GET_ARTICLES({state, commit}: AugmentActionContext, payload: void): Promise<void> {
        if (state.articlePage >= state.articleSize){
            return new Promise<void>(()=>undefined);
        }
        return state.public_api.getArticlesUsingGet({page: state.articlePage, size: 5})
            .then(response=>response.data)
            .then(data=>{
                commit('SET_ARTICLE_SIZE', data.pageable?.page || 0);
                commit('SET_ARTICLE_PAGE', data.pageable?.size || 0);
                return data.content || [];
            })
            .then(d=>commit('SET_ARTICLES', state.articles.concat(d || [])))
            .then(()=>state.articlePage+=1)
            .then(()=>undefined);
    },
    GET_TARIFFS({state, commit}: AugmentActionContext, payload: void): Promise<void> {
        return state.public_api.getAllTariffsUsingGet()
            .then(response=>response.data)
            .catch(()=>[] as Tariff[])
            .then((tariffs)=>commit('SET_TARIFFS', tariffs));
    },
    SAVE_USER({state, commit, dispatch}: AugmentActionContext, payload): Promise<void> {
        return state.api.createUserUsingPost(payload.id || 0, payload)
            .then(()=>dispatch('GET_USERS', undefined));
    },
    CREATE_USER({state, dispatch}: AugmentActionContext, payload): Promise<void> {
        return state.api.createUserUsingPost1(payload)
            .then(()=>dispatch('GET_USERS', undefined))
    },
    GET_USERS({commit, state}: AugmentActionContext, payload: void): Promise<void> {
        return state.api.getAllUsersUsingGet()
            .then((request=>request.data))
            .catch(()=>[])
            .then((data)=>commit('SET_USERS', data));
    },
    GET_USER_ROLES({commit, state}: AugmentActionContext, payload: void): Promise<void> {
        return state.api.getAllUserRolesUsingGet()
            .then((response)=>response.data || [])
            .then(data=>commit('SET_USER_ROLES', data))
    },

    GET_FLAT({commit, state}: AugmentActionContext, payload: number): Promise<Flat> {// сама реализация, payload id,
        return state.public_api.getFlatByIdUsingGet(payload)
            .then(resp=>resp.data)//полученная стр.
            .catch(()=>undefined)//ошибка
            .then(d=>commit('SET_FLAT', d));//мы вызываем сеттор для сохранения
    },
    GET_COMPLEX_FLATS({commit, state}: AugmentActionContext, payload: number): Promise<Flat[]> {
        return state.public_api.getComplexFlatsUsingGet(payload)
        .then(response=>response.data)
            .then((d)=>{
                console.debug("action data", d);
                return d;
        })
        .catch(()=>[])
            .then(d=>commit('SET_COMPLEX_FLATS', d));
    },

    GET_DISTRICTS({commit, state}: AugmentActionContext, payload: void): Promise<District[]> {
        return state.public_api.getDistrictsByCityUsingGet({city_id: state.city?.id || 9})
            .then(response => response.data)
            .catch(()=>[])
            .then(d=>commit('SET_DISTRICTS', d));
    },
    GET_DISTRICTS_REDACTOR({commit, state}: AugmentActionContext, payload: number): Promise<District[]> {
        return state.public_api.getDistrictsByCityUsingGet({city_id: payload || 9})
            .then(response => response.data)
            .catch(()=>[])
            .then(d=>commit('SET_DISTRICTS', d));
    },
    GET_METROS({commit, state}: AugmentActionContext, payload: void): Promise<Metro[]> {
        return state.public_api.getCityMetrosUsingGet({city_id: state.city?.id || 9})
            .then(response => response.data)
            .catch(err=>[])
            .then(d=>commit('SET_METROS', d));
    },
    GET_METROS_REDACTOR({commit, state}: AugmentActionContext, payload: number): Promise<Metro[]> {
        return state.public_api.getCityMetrosUsingGet({city_id: payload || 9})
            .then(response => response.data)
            .catch(err=>[])
            .then(d=>commit('SET_METROS', d));
    },
    GET_CITIES(context: AugmentActionContext, payload: null): Promise<City[]> {
        return context.getters.public_api.getAllCitiesUsingGet()
            .then(response => response.data)
            .catch(err => [])
            .then(d=>context.commit('SET_CITIES', d));
    },
    GET_CITIES_REDACTOR(context: AugmentActionContext, payload: null): Promise<City[]> {
        return context.getters.public_api.getAllCitiesUsingGet()
            .then(response => response.data)
            .then(data => {
                return data;
            })
            .catch(err => {
                return[];
            })
            .then(d=>context.commit('SET_CITIES_REDACTOR', d));
    },
    GET_SEARCHED_COMPLEXES({ commit, state }: AugmentActionContext,
                             payload: void): Promise<void> {
        if (state.complexSearchParamsChanged){
            commit('SET_COMPLEXES', []);
            state.complexPage = 0;
            state.complexSize = 1;
            state.complexSearchParamsChanged = false;
        }
        console.debug(`complexPage`, state.complexPage)
        if (state.complexPage >= state.complexSize){
            return new Promise<void>(()=>undefined);
        }
        state.complexesLoading = true;
        return state.public_api.getComplexesUsingPost(
            {
                cityId: state.city?.id || 9,
                estateCategory: state.complex_category,
                metroId: state.metro?.id,
                districtId: state.district?.id,
                amountOfRooms: state.amountOfRooms,
                priceMin: state.minComplexPrice,
                priceMax: state.maxComplexPrice,
                deliveryDate: state.complexDeliveryDate
            }, {sort: state.sort, page: state.complexPage})
            .then(response=>response.data)
            .then(data=>{
                console.debug('got data', data);
                commit('SET_COMPLEX_PAGE', data.number || 0);
                commit('SET_COMPLEX_SIZE', data.totalPages || 0);
                state.complexesLoading = false;
                return data.content;
            })
            .then(d=>commit('SET_COMPLEXES', state.complexes.concat(d || [])))
            .then(()=>state.complexPage+=1)
            .then(()=>undefined)
            .catch((err)=>undefined);
    },
    GET_ADVERTIZED_COMPLEXES({commit, state}: AugmentActionContext, payload: void): Promise<void> {
        return state.public_api.getAdvertizedUsingPost(
            {
                cityId: state.city?.id || 9,
                estateCategory:state.complex_category
            })
            .then(response => {
                return response.data
            }).catch(()=>[] as Complex[])
            .then(d=>commit('SET_COMPLEXES', d));
    },
    GET_COMPLEX({commit, state}: AugmentActionContext, payload: number): Promise<Complex> {
        return state.public_api.findByIdUsingGet(payload, {})
            .then(r=>r.data)
            .then(d=>commit('SET_COMPLEX', d));
    },
    GET_AUTH_TOKEN({commit, dispatch, state}, payload: Request ): Promise<void> {
       return state.auth.authUsingPost(payload)
           .then(result=>result.data.token || null)
           .catch(()=>null)
           .then(token=>commit('SET_TOKEN', token))
           .then(()=>dispatch('GET_AUTHORITIES', undefined));
    }
    
}