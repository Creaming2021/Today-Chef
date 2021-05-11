<template>
        <div class="page login">
            <section class="section section__landing">
                <div class="section__heading">
                    <span class="section__title">로그인</span>
                </div>
                <div class="section__content">
                    <Error :errorMessage="errorMessage" />
                    <p class="section__lead">로그인화면</p>
                    <form @submit.prevent="handleSubmit" class="form">
                        <span class="form__lead">
                            <ion-icon name="rocket" class="icon"></ion-icon>We are excited to see you again!
                        </span>
                        <br />
                        <div class="form__input-group">
                            <ion-icon name="person" class="form__icon"></ion-icon>
                            <input
                                type="email"
                                name="email"
                                class="form__control"
                                placeholder="Enter Email"
                                required
                                v-model.trim="email"
                            />
                            <label for="email" class="form__label">Email</label>
                        </div>
                        <div class="form__info-group">
                            <span>Don't have an account?</span>
                            <router-link to="/register" class="form__link btn btn--rounded">Register</router-link>
                        </div>
                        <Error :errors="errors" />
                        <button type="submit" class="form__submit">Login</button>
                    </form>
                </div>
            </section>
    </div>
</template>

<script>
import { mapActions } from 'vuex';
// import setAuthToken from '../../utils/authToken';
import { chat } from '@/api/instance.js';

export default {
    name: 'Login',
    props: ['message'],
    components: {
        Error,
    },
    data: function() {
        return {
            email: '',
            errorMessage: this.message,
            errors: []
        };
    },
    methods: {
        ...mapActions(['saveChatUserData', 'toggleAuthState']),
        
        handleSubmit() {
            this.errors = [];
            if (this.email) {
                chat.post('auth/login', {
                        email: this.email,
                    })
                    .then(res => {
                        if (res.data.errors) {
                            for (const error of res.data.errors) {
                                const [key] = Object.keys(error);
                                const [value] = Object.values(error);
                                this.errors.push({
                                    key,
                                    value
                                });
                            }
                        } else {
                            // 만약 쓰게 된다면,, Spring server에서 쓰거라....
                            // localStorage.setItem('authToken', res.data.token);
                            // this.$store.dispatch('toggleAuthState', true);
                            this.$store.dispatch('saveChatUserData', res.data.user);

                            // setAuthToken(res.data.token);

                            // this.$router.push({
                            //     name: 'UserProfile',
                            //     params: { handle: res.data.user.handle }
                            // });
                            this.$router.push({
                                name: 'RoomList',
                            });
                        }
                    });
            }

            setTimeout(() => {
                this.errors = [];
            }, 1500);
        }
    },
    mounted() {
        if (this.errorMessage) {
            setTimeout(() => {
                this.errorMessage = '';
            }, 1500);
        }
    }
};
</script>

 
<!--
<style lang="scss">
@import '@/assets/scss/components/form.scss';
</style>
-->
