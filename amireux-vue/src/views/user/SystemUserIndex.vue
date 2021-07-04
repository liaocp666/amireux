<template>
  <page-header-wrapper>
    <template v-slot:extra>
      <a-button type="primary" icon="plus" @click="$refs.SystemUserAdd.preData()">添加</a-button>
      <a-popconfirm
        title="确定批量删除选中记录吗?"
        ok-text="确定"
        cancel-text="取消"
        @cancel="$message.warn('取消批量删除')"
        @confirm="onBatchDelete"
      >
        <a-button type="primary" icon="delete">批量删除</a-button>
      </a-popconfirm>
    </template>
    <a-card>
      <a-row>
        <a-form-model
          :model="queryParam"
          ref="form"
          :label-col="labelCol"
          :wrapper-col="wrapperCol">
          <a-col span="8">
            <a-form-model-item label="用户名" prop="username">
              <a-input v-model="queryParam.username" placeholder="用户名" />
            </a-form-model-item>
          </a-col>
          <a-col span="8">
            <a-form-model-item label="启用" prop="enable">
              <a-select v-model="queryParam.enable" placeholder="是否启用" allowClear>
                <a-select-option :value="1">启用</a-select-option>
                <a-select-option :value="0">停用</a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col span="8" v-if="expand">
            <a-form-model-item label="手机号" prop="phone">
              <a-input v-model="queryParam.phone" placeholder="手机号" />
            </a-form-model-item>
          </a-col>
          <a-col span="8" v-if="expand">
            <a-form-model-item label="邮箱" prop="email">
              <a-input v-model="queryParam.email" placeholder="邮箱" />
            </a-form-model-item>
          </a-col>
          <a-col span="8">
            <a-form-model-item :label-col="{ span: 0 }" :wrapper-col="{ span: 23 }">
              <a-button-group>
                <a-button type="primary" @click="loadTableData">查询</a-button>
                <a-button type="primary" :style="{ marginLeft: '15px' }" @click="onReset">重置</a-button>
                <a :style="{ marginLeft: '15px' }" @click="toggle">
                  高级查询&nbsp;<a-icon :type="expand ? 'up' : 'down'" />
                </a>
              </a-button-group>
            </a-form-model-item>
          </a-col>
        </a-form-model>
      </a-row>
      <a-row>
        <a-col span="24">
          <s-table
            ref="table"
            size="default"
            :columns="columns"
            :data="loadData"
            :rowKey="record => record.id"
            :rowSelection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }">
            <template slot="avatar" slot-scope="record">
              <a-avatar shape="square" :src="record.avatar" />
            </template>
            <template slot="enable" slot-scope="record">
              <a-tag color="green" v-if="record.enable">
                启用
              </a-tag>
              <a-tag color="red" v-else>
                禁用
              </a-tag>
            </template>
            <template slot="action" slot-scope="record">
              <a @click="$refs.SystemUserAuth.preData(record.id)">授权</a>
              <a-divider type="vertical" />
              <a @click="$refs.SystemUserEdit.preData(record.id)">编辑</a>
              <a-divider type="vertical" />
              <a-popconfirm
                title="确定删除吗?"
                ok-text="确定"
                cancel-text="取消"
                @cancel="$message.warn('取消删除')"
                @confirm="doDelete(record)"
              >
                <a>删除</a>
              </a-popconfirm>
            </template>
          </s-table>
        </a-col>
      </a-row>
    </a-card>
    <system-user-add
      ref="SystemUserAdd"
      @success="loadTableData"
    />
    <system-user-edit
      ref="SystemUserEdit"
      @success="loadTableData"
    />
    <system-user-auth
      ref="SystemUserAuth"
      @success="loadTableData"
    />
  </page-header-wrapper>
</template>

<script>
import STable from '@/components/Table'
import { queryPage, batchDelete } from '@/api/system/user/SystemUser'
import SystemUserAdd from '@/views/user/SystemUserAdd'
import SystemUserEdit from '@/views/user/SystemUserEdit'
import SystemUserAuth from '@/views/user/SystemUserAuth'

const columns = [
  {
    title: '头像',
    scopedSlots: { customRender: 'avatar' }
  },
  {
    title: '用户名',
    dataIndex: 'username'
  },
  {
    title: '手机',
    dataIndex: 'phone'
  },
  {
    title: '邮箱',
    dataIndex: 'email'
  },
  {
    title: '启用',
    scopedSlots: { customRender: 'enable' }
  },
  {
    title: '操作',
    align: 'center',
    width: '20%',
    scopedSlots: { customRender: 'action' }
  }
]

export default {
  name: 'SystemUserIndex',
  components: { STable, SystemUserAdd, SystemUserEdit, SystemUserAuth },
  data () {
    return {
      labelCol: { span: 4 },
      wrapperCol: { span: 19 },
      columns: columns,
      queryParam: {},
      expand: false,
      loadData: parameter => {
        return queryPage(Object.assign(parameter, this.queryParam)).then(resp => {
          return resp.data
        })
      },
      selectedRowKeys: [],
      selectedRows: []
    }
  },
  methods: {
    onReset () {
      this.queryParam = {}
      this.loadTableData()
    },
    onSelectChange (selectedRowKeys, selectedRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectedRows = selectedRows
    },
    doDelete (item) {
      const data = [item.id]
      batchDelete(data).then(resp => {
        if (resp.code !== 2000) {
          return
        }
        this.$message.success('删除成功')
        this.loadTableData()
      })
    },
    onBatchDelete () {
      batchDelete(this.selectedRowKeys).then(resp => {
        if (resp.code !== 2000) {
          this.$message.error(resp.msg)
          return
        }
        this.$message.success('删除成功')
        this.loadTableData()
      })
    },
    loadTableData () {
      this.$refs.table.refresh(true)
    },
    toggle () {
      this.expand = !this.expand
      if (!this.expand) {
        this.queryParam.phone = ''
        this.queryParam.nickname = ''
        this.queryParam.email = ''
      }
    }
  }
}
</script>

<style scoped>

</style>
