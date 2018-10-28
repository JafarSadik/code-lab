const app = new Vue({
    el: '#root',
    data: {
        title: 'Vue.js basics',
        description: 'This example presents a basic usage of Vue.js',
        currentTime: new Date()
    },
    computed: {
        time: function() {
            setInterval(() => this.currentTime = new Date(), 100);
            return this.currentTime;
        }
    },
});