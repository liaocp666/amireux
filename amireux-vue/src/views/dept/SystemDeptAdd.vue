<template>
  <a-modal
    title="增加部门"
    :visible="visible"
    width="900px"
    :confirm-loading="confirmLoading"
    @ok="handleOk"
    @cancel="visible = false">
    <a-row>
      <a-spin :spinning="spinning">
        <a-form-model
          :model="form"
          ref="form"
          :label-col="labelCol"
          :rules="rules"
          :wrapper-col="wrapperCol">
          <a-col span="12">
            <a-form-model-item label="上级权限" prop="parentId">
              <a-tree-select
                v-model="form.parentId"
                style="width: 100%"
                :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                :tree-data="treeData"
                placeholder="请选择上级权限名称"
                searchPlaceholder="输入名称搜索"
                show-search
                :filterTreeNode="treeSelectSearch"
              />
            </a-form-model-item>
          </a-col>
          <a-col span="12">
            <a-form-model-item label="部门名称" prop="title">
              <a-input placeholder="请输入部门名称" v-model="form.title" />
            </a-form-model-item>
          </a-col>
          <a-col span="12">
            <a-form-model-item label="部门标识" prop="keyword">
              <a-input placeholder="请输入部门唯一标识" v-model="form.keyword" />
            </a-form-model-item>
          </a-col>
          <a-col span="12">
            <a-form-model-item label="部门序号" prop="sortNum">
              <a-input-number style="width: 100%" :min="1" :max="99" placeholder="请输入部门序号" v-model="form.sortNum" />
            </a-form-model-item>
          </a-col>
        </a-form-model>
      </a-spin>
    </a-row>
  </a-modal>
</template>

<script>
import { addDept, getTree } from '@/api/system/dept/SystemDept'

export default {
  name: 'SystemDeptAdd',
  data () {
    return {
      visible: false,
      treeData: [{
        title: '/',
        value: '0',
        key: '0',
        children: []
      }],
      spinning: false,
      confirmLoading: false,
      labelCol: { span: 5, offset: 1 },
      wrapperCol: { span: 16 },
      rules: {
        title: [
          { required: true, message: '请输入部门名称', trigger: 'blur' }
        ],
        seqNum: [
          { required: true, message: '请输入部门序号', trigger: 'blur' }
        ],
        keyword: [
          { required: true, message: '请输入部门唯一标识', trigger: 'blur' }
        ]
      },
      form: {
        sortNum: 1,
        parentId: 0
      }
    }
  },
  methods: {
    treeSelectSearch (searchVal, treeNode) {
      return treeNode.data.props.title.includes(searchVal)
    },
    preData (parentId) {
      this.spinning = true
      this.form = {
        sortNum: 1,
        parentId: parentId
      }
      getTree({ pageNo: 0, pageSize: 9999 }).then(resp => {
        this.treeData[0].children = resp
        this.spinning = false
        this.visible = true
        this.confirmLoading = false
      })
    },
    handleOk () {
      this.$refs.form.validate(valid => {
        if (!valid) {
          return false
        }
        this.confirmLoading = true
        addDept(this.form).then(resp => {
          if (resp.code === 2000) {
            this.visible = false
            this.$emit('success')
          }
        }).finally(() => {
          this.confirmLoading = false
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
