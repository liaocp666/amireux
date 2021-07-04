<template>
  <a-modal
    title="编辑角色"
    :visible="visible"
    width="900px"
    :confirm-loading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="spinning">
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
    </a-spin>
  </a-modal>
</template>

<script>
import { editRole, detail } from '@/api/system/role/SystemRole'

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
      wrapperCol: { span: 16 },
      id: '',
      spinning: true
    }
  },
  methods: {
    preData (id) {
      this.visible = true
      this.confirmLoading = false
      this.id = id
      this.loadData()
    },
    loadData () {
      this.spinning = true
      detail({ 'id': this.id }).then(resp => {
        this.spinning = false
        if (resp.code !== 2000) {
          return
        }
        this.form = resp.data
      })
    },
    handleOk () {
      this.confirmLoading = true
      this.$refs.form.validate(valid => {
        if (!valid) {
          return false
        }
        editRole(this.form).then(resp => {
          if (resp.code === 2000) {
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
