<template>
  <a-modal
    title="用户授权"
    :visible="visible"
    width="500px"
    :confirm-loading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
  >
    <a-spin :spinning="spinning">
      <a-tree
        v-model="checkedKeys"
        checkable
        showLine
        :checkStrictly="checkStrictly"
        :tree-data="treeData"
      />
    </a-spin>
  </a-modal>
</template>

<script>
import { queryByUser, queryPage } from '@/api/system/role/SystemRole'
import { auth } from '@/api/system/user/SystemUser'

export default {
  name: 'SystemUserAuth',
  data () {
    return {
      confirmLoading: false,
      visible: false,
      spinning: false,
      treeData: [],
      checkedKeys: [],
      userId: '',
      checkStrictly: true,
      enableSwitch: false
    }
  },
  methods: {
    enableCheckStrictly (value, event) {
      this.enableSwitch = value
      this.checkStrictly = !value
    },
    preData (userId) {
      this.visible = true
      this.userId = userId
      this.spinning = true
      queryPage({ pageNo: 0, pageSize: 9999 }).then(resp => {
        this.treeData = []
        resp.data.content.forEach(item => {
          this.treeData.push({
            value: item.id,
            title: item.title,
            key: item.id
          })
        })
        this.loadData()
      })
    },
    // 加载当前角色拥有的权限
    loadData () {
      queryByUser({ 'userId': this.userId }).then(resp => {
        this.checkedKeys = []
        resp.data.forEach(item => {
          this.checkedKeys.push(item.id)
        })
        this.spinning = false
      })
    },
    handleOk () {
      const data = {
        'userId': this.userId,
        'roleIds': this.checkedKeys.checked
      }
      this.confirmLoading = true
      auth(data).then(resp => {
        this.confirmLoading = false
        if (resp.code !== 2000) {
          return
        }
        this.$message.success('授权成功')
        this.handleCancel()
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
