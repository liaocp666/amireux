<template>
  <page-header-wrapper>
    <template v-slot:extra>
      <a-button type="primary" icon="plus" @click="$refs.SystemRoleAdd.preData()">添加</a-button>
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
            <a-form-model-item label="名称" prop="name">
              <a-input v-model="queryParam.name" placeholder="角色名称"/>
            </a-form-model-item>
          </a-col>
          <a-col span="8">
            <a-form-model-item label="状态" prop="enable">
              <a-select v-model="queryParam.enable" placeholder="角色状态" allowClear>
                <a-select-option :value="1">启用</a-select-option>
                <a-select-option :value="0">停用</a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col span="8">
            <a-form-model-item :label-col="{ span: 0 }" :wrapper-col="{ span: 23 }">
              <a-button-group>
                <a-button type="primary" @click="loadTableData">查询</a-button>
                <a-button type="primary" :style="{ marginLeft: '15px' }" @click="onReset">重置</a-button>
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
            <template slot="enable" slot-scope="record">
              <a-tag color="green" v-if="record.enable">
                启用
              </a-tag>
              <a-tag color="red" v-else>
                禁用
              </a-tag>
            </template>
            <template slot="action" slot-scope="record">
              <a @click="$refs.SystemRoleAuth.preData(record.id)">授权</a>
              <a-divider type="vertical"/>
              <a @click="$refs.SystemRoleEdit.preData(record.id)">编辑</a>
              <a-divider type="vertical"/>
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
    <!--  角色授权   -->
    <system-role-auth ref="SystemRoleAuth"/>
    <!--  角色添加  -->
    <system-role-add ref="SystemRoleAdd" @success="loadTableData"/>
    <!--  角色编辑  -->
    <system-role-edit ref="SystemRoleEdit" @success="loadTableData"/>
  </page-header-wrapper>
</template>

<script>
import STable from '@/components/Table'
import { queryPage, deleteRole, batchDelete } from '@/api/system/role/SystemRole'
import SystemRoleAuth from '@/views/system/role/SystemRoleAuth'
import SystemRoleAdd from '@/views/system/role/SystemRoleAdd'
import SystemRoleEdit from '@/views/system/role/SystemRoleEdit'
import TagSelectOption from '@/components/TagSelect/TagSelectOption'

const columns = [
  {
    title: '名称',
    dataIndex: 'name'
  },
  {
    title: '备注',
    dataIndex: 'remark'
  },
  {
    title: '状态',
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
  name: 'SystemRoleIndex',
  components: {
    TagSelectOption,
    STable,
    SystemRoleAuth,
    SystemRoleAdd,
    SystemRoleEdit
  },
  data () {
    return {
      columns: columns,
      loadData: parameter => {
        return queryPage(Object.assign(parameter, this.queryParam)).then(resp => {
          return resp.data
        })
      },
      selectedRowKeys: [],
      labelCol: { span: 3 },
      wrapperCol: { span: 19 },
      queryParam: {}
    }
  },
  methods: {
    onReset () {
      this.queryParam = {}
      this.loadTableData()
    },
    loadTableData () {
      this.$refs.table.refresh(true)
    },
    onSelectChange (selectedRowKeys, selectedRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectedRows = selectedRows
    },
    doDelete (item) {
      const data = {
        'id': item.id
      }
      deleteRole(data).then(resp => {
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
    }
  }
}
</script>

<style scoped>

</style>
