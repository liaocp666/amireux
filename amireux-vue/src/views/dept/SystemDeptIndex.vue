<template>
  <page-header-wrapper>
    <template v-slot:extra>
      <a-button type="primary" icon="plus" @click="$refs.SystemDeptAdd.preData('0')">添加</a-button>
    </template>
    <a-card>
      <s-table
        ref="table"
        :rowKey="(record) => record.id"
        size="default"
        :columns="columns"
        :data="loadData"
      >
        <template slot="enable" slot-scope="record">
          <a-tag color="green" v-if="record.enable">
            启用
          </a-tag>
          <a-tag color="red" v-else>
            禁用
          </a-tag>
        </template>
        <template slot="action" slot-scope="record">
          <a @click="$refs.SystemDeptAdd.preData(record.id)">添加</a>
          <a-divider type="vertical" />
          <a @click="$refs.SystemDeptEdit.preData(record.id)">编辑</a>
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
    </a-card>
    <system-dept-add ref="SystemDeptAdd" @success="addSuccess" />
    <system-dept-edit ref="SystemDeptEdit" @success="addSuccess"/>
  </page-header-wrapper>
</template>

<script>
import STable from '@/components/Table'
import { queryPage, deleteDept } from '@/api/system/dept/SystemDept'
import SystemDeptAdd from '@/views/dept/SystemDeptAdd'
import SystemDeptEdit from '@/views/dept/SystemDeptEdit'

const columns = [
  {
    title: '名称',
    dataIndex: 'title'
  },
  {
    title: '标识',
    dataIndex: 'keyword'
  },
  {
    title: '序号',
    dataIndex: 'sortNum'
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
  name: 'SystemDeptIndex',
  components: { STable, SystemDeptAdd, SystemDeptEdit },
  data () {
    return {
      columns: columns,
      queryParam: {},
      loadData: parameter => {
        return queryPage(Object.assign(parameter, this.queryParam))
          .then(res => {
            return res.data
          })
      }
    }
  },
  methods: {
    addSuccess () {
      this.$refs.table.refresh()
    },
    doDelete (item) {
      const data = {
        'id': item.id
      }
      deleteDept(data).then(resp => {
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
