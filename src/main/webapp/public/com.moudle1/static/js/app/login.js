

// $(function () {
//
// });
function submitLogin() {

    var param = {
        username: this.loginDatas.username,
        password: this.loginDatas.password,
        'saveLogin': this.saveLogin
    }
    debugger
    this.postRequest('/xboot/login', param) //vm.$data.page4 / this.page4
        .then(function(res){
            if (res.success === true) {
                this.$message({type: 'success', message: '登录成功!'});

            }

        },function(){
            console.log('failed');
        })
        .catch(function (e) {
            // this.formLoading = false;
        } )
    // this.$http.post('/xboot/login', param) //vm.$data.page4 / this.page4
    //     .then(function(res){
    //         if (res.success === true) {
    //             this.$message({type: 'success', message: '登录成功!'});
    //
    //         }
    //
    //     },function(){
    //         console.log('failed');
    //     })
    //     .catch(function (e) {
    //         // this.formLoading = false;
    //     } )
}

function postRequest(url, params) {
    let accessToken = this.getStore("accessToken");
    return axios({  //axios  this.$http
        method: 'post',
        url: '/xboot' + '/login',
        data: params,
        transformRequest: [function (data) {
            let ret = '';
            for (let it in data) {
                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&';
            }
            return ret;
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'accessToken': accessToken
        }
    });
}

function getStore(name) {
    if (!name) return;
    return window.localStorage.getItem(name);
}
