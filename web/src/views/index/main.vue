/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
<template>
    <div class="main-wrapper">
        <!-- <div id="shade" v-if="accountStatus === '1'"></div> -->
        <!-- <div id="reset-password" v-if="accountStatus === '1'">
            <div class="reset-password-title">
                修改密码
            </div>
            <el-form :model="rulePasswordForm" status-icon :rules="rules2" ref="rulePasswordForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="旧密码" prop="oldPass">
                    <el-input type="password" v-model="rulePasswordForm.oldPass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="pass">
                    <el-input type="password" v-model="rulePasswordForm.pass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="rulePasswordForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('rulePasswordForm')" :loading="loading">提交</el-button>
                    <el-button @click="resetForm('rulePasswordForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </div> -->
        <div class="menu-wrapper header" :class="{'menu-show': menuShow,'menu-hide': menuHide}">
            <v-menu @sidebarChange="change($event)" :minMenu="show"></v-menu>
        </div>
        <div class="view-wrapper" :class="{'view-show': menuShow,'view-hide': menuHide}">
            <router-view class="bg-f7f7f7"></router-view>
        </div>
        <set-front :show='frontShow' v-if='frontShow' @close='closeFront'></set-front>
    </div>
</template>

<script>
import sidebar from "./sidebar";
// import setFront from "./dialog/setFront"
// import { resetPassword, addnodes, getGroups } from "@/util/api";
import router from "@/router";
// const sha256 = require("js-sha256").sha256;
export default {
    name: "mains",
    components: {
        "v-menu": sidebar,
        // "set-front": setFront
    },
    data: function() {
        // if (sessionStorage.getItem("reload") == 1) {
        //     sessionStorage.setItem("reload", 2);
        //     router.go(0);
        // }
        // var validatePass = (rule, value, callback) => {
        //     if (value === "") {
        //         callback(new Error("请输入密码"));
        //     } else {
        //         if (this.rulePasswordForm.checkPass !== "") {
        //             this.$refs.rulePasswordForm.validateField("checkPass");
        //         }
        //         callback();
        //     }
        // };
        // var validatePass2 = (rule, value, callback) => {
        //     if (value === "") {
        //         callback(new Error("请再次输入密码"));
        //     } else if (value !== this.rulePasswordForm.pass) {
        //         callback(new Error("两次输入密码不一致!"));
        //     } else {
        //         callback();
        //     }
        // };
        return {
            frontShow: false,
            menuShow: true,
            menuHide: false,
            loading: false,
            accountStatus: 0,
            account: localStorage.getItem("user"),
            rulePasswordForm: {
                oldPass: "",
                pass: "",
                checkPass: ""
            }
        };
    },
    computed: {
        show: function() {
            return this.menuShow;
        }
    },
    // mounted(){
    //     this.getGroupList();
    // },
    methods: {
        change: function(val) {
            this.menuShow = !val;
            this.menuHide = val;
        },
        submitForm(formName) {
            this.$refs[formName].validate(valid => {
                if (valid) {
                    this.loading = true;
                    this.getResetPassword();
                } else {
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        // getResetPassword() {
        //     let reqData = {
        //         oldAccountPwd: sha256(this.rulePasswordForm.oldPass),
        //         newAccountPwd: sha256(this.rulePasswordForm.pass)
        //     };
        //     resetPassword(reqData, {})
        //         .then(res => {
        //             this.loading = false;
        //             if (res.data.code === 0) {
        //                 this.$message({
        //                     type: "success",
        //                     message: "密码修改成功"
        //                 });
        //                 this.accountStatus = "2";
        //                 sessionStorage.setItem(
        //                     "accountStatus",
        //                     this.accountStatus
        //                 );
        //             } else {
        //                 this.$message({
        //                     type: "error",
        //                     message: this.errcode.errCode[res.data.code].cn
        //                 });
        //             }
        //         })
        //         .catch(err => {
        //             this.$message({
        //                 type: "error",
        //                 message: "密码修改失败"
        //             });
        //             this.$message.closeAll()
        //         });
        // },
        // getGroupList: function(){
        //     getGroups().then(res => {
        //         if(res.data.code === 0){
        //             if(res.data.data && res.data.data.length){
        //                 if(!localStorage.getItem("groupId")){
        //                     localStorage.setItem("groupId",res.data.data[0].groupId)
        //                 }
        //                 if(!localStorage.getItem("groupName")){
        //                     localStorage.setItem("groupName",res.data.data[0].groupName);
        //                 }
        //                 this.accountStatus = sessionStorage.getItem("accountStatus");
        //                 if(this.$route.path && this.$route.path !== "/main"){
        //                     router.push(this.$route.path)
        //                 }else if(this.$route.path == "/main"){
        //                     router.push("/home")
        //                 }else{
        //                     router.push("/home")
        //                 }
        //             }else{
        //                 this.frontShow = true
        //             }
        //         }else{
        //             this.frontShow = true
        //              this.$message({
        //                 type: "error",
        //                 message: this.errcode.errCode[res.data.code].cn || '查询群组失败'
        //             });
        //         }
        //     }).catch(err => {
                
        //         this.$message({
        //                 type: "error",
        //                 message: "系统错误"
        //             });
        //         this.$message.closeAll()
        //     })
        // },
        // closeFront: function(){
        //     this.frontShow = false;
        //     this.getGroupList()
        // }
    }
};
</script>
<style scoped>
.header .el-menu {
    height: 100%;
}
.main-wrapper {
    width: 100%;
    background: #f7f7f7;
    height: 100%;
}
.main-wrapper::after {
    display: block;
    content: "";
    clear: both;
}
.menu-wrapper {
    height: 100%;
    position: fixed;
    left: 0;
    top: 0;
    bottom: 0;
    overflow-x: hidden;
    overflow-y: auto;
}
.view-wrapper {
    height: 100%;
    padding-left: 200px;
}
.menu-show {
    width: 200px;
    transition: width 0.5s;
    -moz-transition: width 0.5s;
    -webkit-transition: width 0.5s;
    -o-transition: width 0.5s;
}
.menu-hide {
    /* width: 56px; */
    width: 56px;
    transition: width 0.5s;
    -moz-transition: width 0.5s;
    -webkit-transition: width 0.5s;
    -o-transition: width 2s;
}
.view-show {
    /* padding-left: 200px; */
    width: calc(100% - 200px);
    transition: width 0.5s;
    -moz-transition: width 0.5s;
    -webkit-transition: width 0.5s;
    -o-transition: width 0.5s;
}
.view-hide {
    padding-left: 56px;
    /* width: calc(100% - 56px); */
    transition: width 0.5s;
    -moz-transition: width 0.5s;
    -webkit-transition: width 0.5s;
    -o-transition: width 2s;
}
#shade {
    position: absolute;
    top: 0;
    left: 0;
    z-index: 2;
    width: 100%;
    height: 100%;
    background-color: #000;
    opacity: 0.3;
    /*兼容IE8及以下版本浏览器*/
    filter: alpha(opacity=30);
}

#reset-password {
    position: absolute;
    z-index: 3;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    width: 470px;
    height: 273px;
    margin: auto;
    background-color: #fff;
    padding-top: 15px;
    /* padding-right: 25px; */
}
.reset-password-title {
    margin-bottom: 5px;
    text-align: center;
    font-size: 16px;
    font-weight: 600;
}
#add-nodes {
    position: absolute;
    z-index: 3;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    width: 470px;
    height: 140px;
    margin: auto;
    background-color: #fff;
    padding-top: 15px;
}
.add-nodes-title {
    margin-bottom: 5px;
    text-align: center;
    font-size: 16px;
    font-weight: 600;
}
.demo-ruleForm {
    padding-right: 25px;
}
</style>
