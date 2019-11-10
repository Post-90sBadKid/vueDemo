<template>
  <div class="container-fluid col-md-2 col-md-offset-5">
    <form class="form-horizontal">
      <div class="form-group">
        <label for="exampleInputEmail1">账号：</label>
        <input
          type="text"
          class="form-control"
          v-model="userName"
          id="exampleInputEmail1"
          placeholder="UserName"
        >
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">密码：</label>
        <input
          type="password"
          class="form-control"
          id="exampleInputPassword1"
          v-model="passWord"
          placeholder="Password"
        >
      </div>
      <div class="checkbox">
        <label>
          <input type="checkbox"> Check me out
        </label>
      </div>
      <button type="button" @click="login()" class="btn btn-default">登陆</button>
    </form>
  </div>
</template>
<script>
export default {
  data () {
    return {
      userName:'',
      passWord:'',
    }
  },
  methods: {
    //登录方法
    login () {
      this.$axios({
        method: 'post',
        url: '/login',
        data: {
          userName: this.userName,
          passWord: this.passWord
        },
      }).then((response) => {
        console.log(response.data);
        // 此处因为ES6箭头函数上下文穿透，this的上下文为外层的this，即Vue实例
        // 跳转到index 页面
        if (response.data.success == true) {
          //登录成功后把信息存储到 localStorage
          sessionStorage.setItem("token", JSON.stringify(response.data.message));
          this.$router.push('index');
        }
      }).catch(function (response) {
        console.error(response);
      });
    }
  }
}
</script>

<style scoped>
</style>

