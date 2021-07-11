<template>
  <a-modal
    title="增加角色"
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
        :wrapper-col="wrapperCol">
        <a-col span="12">
          <a-form-model-item
            label="角色名称"
            prop="name">
            <a-input v-model="form.title" placeholder="请输入角色名称"/>
          </a-form-model-item>
        </a-col>
        <a-col span="12">
          <a-form-model-item label="是否启用" prop="enable">
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
        <a-col span="12">
          <a-form-model-item label="唯一标识" prop="keyword">
            <a-input v-model="form.keyword" placeholder="请输入唯一标识"/>
          </a-form-model-item>
        </a-col>
        <a-col span="12">
          <a-form-model-item label="备注" prop="remark">
            <a-input v-model="form.remark" placeholder="请输入备注"/>
          </a-form-model-item>
        </a-col>
      </a-form-model>
    </a-row>
  </a-modal>
</template>

<script>
import { addRole } from '@/api/system/role/SystemRole'

export default {
  name: 'SystemRoleAdd',
  data () {
    return {
      confirmLoading: false,
      visible: false,
      form: {},
      rules: {
        title: [
          { required: true, message: '请输入角色名称', trigger: 'blur' }
        ],
        enable: [
          { required: true, message: '请选择是否启用', trigger: 'blur' }
        ]
      },
      labelCol: { span: 5, offset: 1 },
      wrapperCol: { span: 16 }
    }
  },
  methods: {
    preData () {
      this.visible = true
      this.confirmLoading = false
      this.form = {}
      this.form.enable = true
    },
    handleOk () {
      this.$refs.form.validate(valid => {
        if (!valid) {
          return false
        }
        this.confirmLoading = true
        addRole(this.form).then(resp => {
          if (resp.code === 2000) {
            this.preData(this.form.parentId)
            this.visible = false
            this.$emit('success')
          }
        }).finally(() => {
          this.confirmLoading = false
        })
      })
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
