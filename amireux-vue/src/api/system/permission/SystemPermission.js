import request from '@/utils/request'

const systemPermissionApi = {
  Page: '/api/v1/permission/page',
  Delete: '/api/v1/permission/delete',
  Add: '/api/v1/permission/save',
  Edit: '/api/v1/permission/edit',
  Get: '/api/v1/permission/detail',
  QueryByRole: '/api/v1/permission/queryByRole'
}

/**
 * 查询角色拥有的权限
 * @param parameter
 */
export function queryByRole (parameter) {
  return request({
    url: systemPermissionApi.QueryByRole,
    method: 'get',
    params: parameter
  })
}

export async function queryPage (parameter) {
  const res = await request({
    url: systemPermissionApi.Page,
    method: 'get',
    params: parameter
  })
  return res
}

export async function getTree (parameter) {
  const res = await request({
    url: systemPermissionApi.Page,
    method: 'get',
    params: parameter
  })
  const tree = []
  res.data.content.forEach(item => {
    tree.push(buildTreeSelect(item))
  })
  return tree
}

export function buildTreeSelect (item) {
  item.value = item.id
  item.key = item.id
  if (item.children && item.children.length > 0) {
    item.children.forEach(ch => buildTreeSelect(ch))
  }
  return item
}

export function deletePermission (parameter) {
  return request({
    url: systemPermissionApi.Delete,
    method: 'post',
    data: parameter
  })
}

export function addPermission (parameter) {
  return request({
    url: systemPermissionApi.Add,
    method: 'post',
    data: parameter
  })
}

export function editPermission (parameter) {
  return request({
    url: systemPermissionApi.Add,
    method: 'post',
    data: parameter
  })
}

export function getPermission (parameter) {
  return request({
    url: systemPermissionApi.Get,
    method: 'get',
    params: parameter
  })
}
