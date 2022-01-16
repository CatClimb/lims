import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    RegisterIndialogVisible:false,
    isCollapse: false,
    isLoading:false,
    
  },
  mutations: {
    C_RegisterIndialogVisible(state){
      state.RegisterIndialogVisible=!state.RegisterIndialogVisible
    },
    SET_COLLAPSE(state){
      state.isCollapse=!state.isCollapse;
    },
    SET_LOADING_TRUE(state){
      state.loading=true;
    },
    SET_LOADING_FALSE(state){
      state.loading=false;
    }
  },
  actions: {
  },
  modules: {
  }
})
