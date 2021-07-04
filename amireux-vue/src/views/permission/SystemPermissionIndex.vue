<template>
  <page-header-wrapper>
    <template v-slot:extra>
      <a-button type="primary" icon="plus" @click="$refs.SystemPermissionAdd.preData('0')">添加</a-button>
    </template>
    <a-card>
      <s-table
        ref="table"
        :rowKey="(record) => record.id"
        size="default"
        :columns="columns"
        :data="loadData"
      >
        <template slot="type" slot-scope="record">
          <a-tag color="pink" v-if="record.type === 'api'">
            接口
          </a-tag>
          <a-tag color="green" v-if="record.type === 'menu'">
            目录
          </a-tag>
          <a-tag color="cyan" v-if="record.type === 'page'">
            页面
          </a-tag>
        </template>
        <template slot="icon" slot-scope="record">
          <a-icon :type="record.icon" v-if="record.icon" />
        </template>
        <template slot="display" slot-scope="record">
          <a-tag v-if="record.enable" color="orange">
            显示
          </a-tag>
          <a-tag v-else>
            隐藏
          </a-tag>
        </template>
        <template slot="action" slot-scope="record">
          <a-button-group size="default">
            <a @click="$refs.SystemPermissionAdd.preData(record.id)">添加</a>
            <a-divider type="vertical" />
            <a @click="$refs.SystemPermissionEdit.preData(record.id)">编辑</a>
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
          </a-button-group>
        </template>
      </s-table>
    </a-card>
    <system-permission-add ref="SystemPermissionAdd" @success="addSuccess"></system-permission-add>
    <system-permission-edit ref="SystemPermissionEdit" @success="addSuccess"></system-permission-edit>
  </page-header-wrapper>
</template>

<script>
import SystemPermissionAdd from '@/views/permission/SystemPermissionAdd'
import { queryPage, deletePermission } from '@/api/system/permission/SystemPermission'
import SystemPermissionEdit from '@/views/permission/SystemPermissionEdit'
import STable from '@/components/Table'

const columns = [
  {
    title: '名称',
    dataIndex: 'title'
  },
  {
    title: '地址',
    dataIndex: 'url'
  },
  {
    title: '类型',
    align: 'center',
    scopedSlots: { customRender: 'type' }
  },
  {
    title: '图标',
    align: 'center',
    scopedSlots: { customRender: 'icon' }
  },
  {
    title: '序号',
    dataIndex: 'sortNum',
    align: 'center'
  },
  {
    title: '是否显示',
    align: 'center',
    scopedSlots: { customRender: 'display' }
  },
  {
    title: '操作',
    align: 'center',
    scopedSlots: { customRender: 'action' }
  }
]

export default {
  name: 'SystemPermissionIndex',
  components: {
    SystemPermissionAdd,
    SystemPermissionEdit,
    STable
  },
  data () {
    return {
      expand: false,
      searchLoading: false,
      form: {},
      columns: columns,
      dataSource: [],
      loading: false,
      loadData: parameter => {
        return queryPage(Object.assign(parameter, this.queryParam))
          .then(res => {
            return res.data
          })
      }
    }
  },
  created () {
  },
  methods: {
    // 添加成功
    addSuccess () {
      this.$refs.table.refresh(true)
    },
    // 删除
    doDelete (item) {
      const data = {
        'id': item.id
      }
      deletePermission(data).then(resp => {
        if (resp.code !== 2000) {
          return
        }
        this.$message.success('删除成功')
        this.$refs.table.refresh(true)
      })
    }
  }
}
</script>

<style scoped>

</style>
