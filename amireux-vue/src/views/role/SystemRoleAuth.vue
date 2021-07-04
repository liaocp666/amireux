<template>
  <a-modal
    title="角色授权"
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
import { getTree, queryByRole } from '@/api/system/permission/SystemPermission'
import { auth } from '@/api/system/role/SystemRole'

export default {
  name: 'SystemRoleAuth',
  data () {
    return {
      confirmLoading: false,
      visible: false,
      spinning: false,
      treeData: [],
      checkedKeys: [],
      roleId: '',
      checkStrictly: true,
      enableSwitch: false
    }
  },
  methods: {
    enableCheckStrictly (value, event) {
      this.enableSwitch = value
      this.checkStrictly = !value
    },
    async preData (roleId) {
      this.visible = true
      this.roleId = roleId
      this.spinning = true
      getTree().then(resp => {
        this.treeData = resp
        this.loadData()
      })
    },
    // 加载当前角色拥有的权限
    loadData () {
      queryByRole({ 'roleId': this.roleId }).then(resp => {
        this.checkedKeys = []
        resp.data.forEach(item => {
          this.checkedKeys.push(item.id)
        })
        this.spinning = false
      })
    },
    handleOk () {
      let permissionIds = []
      if (this.enableSwitch) {
        this.checkedKeys.forEach(item => {
          permissionIds.push(item)
        })
      } else {
        permissionIds = this.checkedKeys.checked
      }
      const data = {
        'roleId': this.roleId,
        'permissionIds': permissionIds
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
