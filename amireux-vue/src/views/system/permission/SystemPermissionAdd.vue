<template>
  <a-modal
    title="增加权限"
    :visible="visible"
    width="900px"
    :confirm-loading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
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
                :treeDefaultExpandedKeys="['0']"
              />
            </a-form-model-item>
          </a-col>
          <a-col span="12">
            <a-form-model-item label="名称" prop="name">
              <a-input v-model="form.name" placeholder="请输入权限名称" />
            </a-form-model-item>
          </a-col>
          <a-col span="12">
            <a-form-model-item label="类型" prop="type">
              <a-select v-model="form.type" placeholder="请选择权限类型" @select="onSelectType">
                <a-select-option value="menu">
                  目录
                </a-select-option>
                <a-select-option value="page">
                  页面
                </a-select-option>
                <a-select-option value="api">
                  接口
                </a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col span="12">
            <a-form-model-item label="地址" prop="path">
              <a-input v-model="form.path" placeholder="请输入权限地址" />
            </a-form-model-item>
          </a-col>
          <a-col span="12">
            <a-form-model-item label="页面" prop="component">
              <a-col :span="form.type === 'menu' ? 22 : 24">
                <a-input
                  v-model="form.component"
                  placeholder="请输入页面位置"
                  v-if="form.type !== 'menu'"
                  :disabled="form.type === 'api'"
                  :addon-before="form.type === 'page' ? '@/views' : ''" />
                <a-select v-model="form.component" placeholder="请选择页面组件" v-if="form.type === 'menu'">
                  <a-select-option value="RouteView">
                    RouteView
                  </a-select-option>
                  <a-select-option value="PageView">
                    PageView
                  </a-select-option>
                </a-select>
              </a-col>
              <a-col span="1" v-if="form.type === 'menu'" offset="1">
                <a-popover v-if="form.type === 'menu'">
                  <template slot="content">
                    <p>PageView：带面包屑导航</p>
                    <p>RouteView：不带面包屑导航</p>
                  </template>
                  <a-icon type="question-circle" />
                </a-popover>
              </a-col>
            </a-form-model-item>
          </a-col>
          <a-col span="12">
            <a-form-model-item label="图标" prop="icon">
              <a-input
                :placeholder="typeof form.icon === 'undefined' ? '请选择图标' : ''"
                disabled>
                <a-icon slot="prefix" :type="form.icon" v-if="form.icon" />
                <a-button
                  slot="suffix"
                  icon="search"
                  type="primary"
                  v-if="form.type !== 'api'"
                  @click="iconVisible = true"></a-button>
              </a-input>
            </a-form-model-item>
          </a-col>
          <a-col span="12">
            <a-form-model-item label="是否显示" prop="display">
              <a-radio-group v-model="form.display" :disabled="form.type === 'api'">
                <a-radio :value="true">
                  显示
                </a-radio>
                <a-radio :value="false">
                  不显示
                </a-radio>
              </a-radio-group>
            </a-form-model-item>
          </a-col>
          <a-col span="12">
            <a-form-model-item label="序号" prop="sortNum">
              <a-input-number :style="{ width: '100%' }" v-model="form.sortNum" placeholder="请输入序号"></a-input-number>
            </a-form-model-item>
          </a-col>
          <a-col span="24">
            <a-form-model-item label="备注" prop="remark" :labelCol="{ span: 3 }" :wrapperCol="{ span: 20 }">
              <a-input v-model="form.remark" placeholder="请输入备注" />
            </a-form-model-item>
          </a-col>
        </a-form-model>
      </a-spin>
    </a-row>
    <icon-selector
      :visible="iconVisible"
      width="900px"
      @change="handleIconChange"
      v-show="iconVisible" />
  </a-modal>
</template>

<script>
import { getTree, addPermission } from '@/api/system/permission/SystemPermission'
import IconSelector from '@/components/IconSelector'

export default {
  name: 'SystemPermissionAdd',
  components: { IconSelector },
  data () {
    return {
      form: {
        type: 'page',
        parentId: '0',
        display: true,
        sortNum: 0
      },
      iconVisible: false,
      visible: false,
      confirmLoading: false,
      labelCol: { span: 5, offset: 1 },
      wrapperCol: { span: 16 },
      spinning: false,
      treeData: [{
        title: '根权限',
        value: '0',
        key: '0',
        children: []
      }],
      rules: {
        parentId: [
          { required: true, message: '请选择上级权限', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入权限名称', trigger: 'blur' }
        ],
        path: [
          { required: true, message: '请输入权限地址', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleIconChange (e) {
      this.form.icon = e
      this.iconVisible = false
    },
    onSearch () {
      this.iconVisible = true
    },
    onSelectType (value, node) {
      if (value === 'menu') {
        this.form.component = 'RouteView'
        this.form.display = true
      } else if (value === 'api') {
        this.form.display = false
        this.form.component = null
        this.form.icon = null
      } else if (value === 'page') {
        this.form.component = null
        this.form.display = true
      } else {
        this.form.component = null
        this.form.display = true
      }
    },
    preData (parentId) {
      this.visible = true
      this.confirmLoading = false
      this.spinning = true
      this.form = {
        type: 'page',
        parentId: parentId,
        display: true,
        sortNum: 0
      }
      getTree().then(resp => {
        this.treeData[0].children = resp
        this.spinning = false
      })
    },
    handleOk () {
      this.confirmLoading = true
      this.$refs.form.validate(valid => {
        if (!valid) {
          return false
        }
        addPermission(this.form).then(resp => {
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

/deep/ .ant-input-affix-wrapper .ant-input-disabled ~ .ant-input-suffix .anticon {
  color: #fff;
}

/deep/ .ant-input-affix-wrapper .ant-input-suffix {
  right: 0px
}
</style>
