const [cdnUrl, example1, example2, example3, example4, example5, example6, example7, example8] = [
`<script src="https://cdn.jsdelivr.net/npm/vue"><script>`,

`var app = new Vue({
    el: '#root'
});`,

`const app = new Vue({
    el: '#root',
    data: {
        title: 'Vue.js basics',
        description: 'This example presents a basic usage of Vue.js',
        time: new Date(),
        code: {cdnUrl, example1, example2}
    }`,

    `const app = new Vue({
    ...
    computed: {
        lowerCase() {
            return this.title.toLowerCase();
        }
    },
    ...
});`,

`<div id="root">
    <h3 class="title">{{title}}</h3>
    <p>{{description}}</p>

    <p>The library is hosted on CDN.</p>
    <pre>{{code.cdnUrl}}</pre>

    <h4>Create Vue.js instance and attach it to HTML element</h4>
    <pre>{{code.example1}}</pre>
    ...........`,

`{{time != null ? time : ""}}`,

`if(myVar != null) { return myVar; }
else { return "";}`,

`{{beautifyText(inputText)}}`,


`Vue.component('my-component', {
    template: \`<div><p class="timer">Local time is {{time || ""}}</p></div>\`,
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
});`
];