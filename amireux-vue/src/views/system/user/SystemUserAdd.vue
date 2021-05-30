<template>
  <a-modal
    title="添加用户"
    :visible="visible"
    width="900px"
    :confirm-loading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-row>
      <a-form-model
        :model="form"
        ref="form"
        :label-col="labelCol"
        :rules="rules"
        :wrapper-col="wrapperCol"
      >
        <a-col span="12"></a-col>
        <a-col span="12">
          <a-form-model-item label="用户名" prop="username">
            <a-input v-model="form.username" placeholder="请输入用户名" />
          </a-form-model-item>
        </a-col>
        <a-col span="12">
          <a-form-model-item label="密码" prop="password">
            <a-input type="password" v-model="form.password" placeholder="请输入密码" />
          </a-form-model-item>
        </a-col>
        <a-col span="12">
          <a-form-model-item label="确认密码" prop="confirmPassword">
            <a-input type="password" v-model="form.confirmPassword" placeholder="再次输入密码" />
          </a-form-model-item>
        </a-col>
        <a-col span="12">
          <a-form-model-item label="手机" prop="phone">
            <a-input v-model="form.phone" placeholder="请输入手机" />
          </a-form-model-item>
        </a-col>
        <a-col span="12">
          <a-form-model-item label="昵称" prop="nickname">
            <a-input v-model="form.nickname" placeholder="请输入昵称" />
          </a-form-model-item>
        </a-col>
        <a-col span="12">
          <a-form-model-item label="邮箱" prop="email">
            <a-input v-model="form.email" placeholder="请输入邮箱" />
          </a-form-model-item>
        </a-col>
        <a-col span="12">
          <a-form-model-item label="启用" prop="enable">
            <a-radio-group v-model="form.enable">
              <a-radio :value="true">
                启用
              </a-radio>
              <a-radio :value="false">
                停用
              </a-radio>
            </a-radio-group>
          </a-form-model-item>
        </a-col>
      </a-form-model>
    </a-row>
  </a-modal>
</template>

<script>
export default {
  name: 'SystemUserAdd',
  data () {
    return {
      visible: false,
      confirmLoading: false,
      form: {},
      labelCol: { span: 5, offset: 1 },
      wrapperCol: { span: 16 },
      rules: {}
    }
  },
  methods: {
    preData () {
      this.visible = true
      this.confirmLoading = false
    },
    handleOk () {

    },
    handleCancel () {
      this.visible = false
      this.confirmLoading = false
    }
  }
}
</script>

<style scoped>

</style>
