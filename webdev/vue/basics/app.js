Vue.component('my-component', {
    template: `
        <div>
            <p class="timer">Local time is {{time || ""}}</p>
        </div>
    `,
    data() {
        return {
            time: 0,
        }
    },
    props: {
        milliseconds: [Boolean]
    },
    created() {
        setInterval(() => this.time = formatTime(new Date(), this.milliseconds), 100);
    }
});

const app = new Vue({
    el: '#root',
    data: {
        title: 'Vue.js basics',
        description: 'This example presents a basic usage of Vue.js',
        code: {cdnUrl, example1, example2, example3, example4, example5, example6, example7, example8}
    },
    computed: {
        lowerCase() {
            return this.title.toLowerCase();
        }
    },
    created() {
        console.log("Vue.js app created");
    }
});

function formatTime(date, milliseconds) {
    let time = date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
    if (milliseconds) {
        time += '.' + date.getMilliseconds();
    }
    return time;
}
