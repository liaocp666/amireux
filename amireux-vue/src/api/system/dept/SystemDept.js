import request from '@/utils/request'

const systemDeptApi = {
  Page: '/api/v1/dept/page',
  Delete: '/api/v1/dept/delete',
  Add: '/api/v1/dept/save',
  Edit: '/api/v1/dept/edit',
  Detail: '/api/v1/dept/detail',
  QueryByRole: '/api/v1/dept/queryByRole'
}

export async function queryPage (parameter) {
  const res = await request({
    url: systemDeptApi.Page,
    method: 'get',
    params: parameter
  })
  return res
}

export async function getTree (parameter) {
  const res = await request({
    url: systemDeptApi.Page,
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

/**
 * 添加部门
 * @param data
 * @returns {AxiosPromise}
 */
export function addDept (data) {
  return request({
    url: systemDeptApi.Add,
    method: 'post',
    data: data
  })
}

export function detail (parameter) {
  return request({
    url: systemDeptApi.Detail,
    method: 'get',
    params: parameter
  })
}

export function deleteDept (parameter) {
  return request({
    url: systemDeptApi.Delete,
    method: 'post',
    data: parameter
  })
}
