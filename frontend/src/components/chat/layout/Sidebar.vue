<template>
    <transition name="slideLeft" mode="out-in">
        <div v-if="visible" class="sidebar">
            <div class="sidebar__header">
                <slot name="header">
                    <h4>Default Header</h4>
                </slot>
            </div>
            <div class="sidebar__body">
                <slot name="body"></slot>
            </div>
            <div class="sidebar__footer">
                <slot name="footer"></slot>
            </div>
        </div>
    </transition>
</template>


<script>
export default {
    name: 'Sidebar',
    data: function() {
        return {
            visible: false
        };
    },
    methods: {
        toggle() {
            this.visible = !this.visible;
        }
    },
    mounted() {}
};
</script>


<style lang="scss" scoped>
.sidebar {
    height: 100%;
    position: absolute;
    right: 0px;
    top: 0px;
    background: rgba($color: #f3f2ee, $alpha: 0.9);
    width: 300px;
    overflow: auto;
    display: flex;
    flex-flow: column;
    transition: all 0.5s ease;
    z-index: 100;

    &__header {
        display: flex;
        align-items: center;
        padding: 1rem 0 0 0;
        margin: 0 1rem;
    }

    &__body {
        height: auto;
        overflow-y: auto;
        overflow-x: hidden;
        margin-bottom: 20px;
    }

    &__footer {
        height: auto;
        flex-grow: 1;
        display: flex;
        justify-content: center;
        align-items: flex-end;
        margin-bottom: 85px;
        z-index: 3;
    }
}
</style>
